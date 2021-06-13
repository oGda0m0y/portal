package com.bds.portal.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.bds.model.Balance;
import com.bds.model.BalanceBill;
import com.bds.model.Result;
import com.bds.portal.util.Const;

@Service
public class BalanceService {

	private static final Logger logger = Logger.getLogger(BalanceService.class);
	@Resource
	private Dao apiDao;
	@Resource
	private MessageService messageService;
	/** 默认充值有效期 */
	private final static Integer DEFUALT_VALIDITY_TIME = 30;
	/** 交易锁 */
	private final static Object LOCK = new Object();

	/**
	 * 充值智慧豆
	 * 
	 * @param user_id
	 *            用户id
	 * @param amount
	 *            充值金额
	 * @param balance_type
	 *            类型\r\n1.1个月58\r\n2.1个月158\r\n3.vip合作\r\n4.买次
	 * @param validity_time
	 *            充值有效期(可选参数,默认30天)
	 * @param transaction_id
	 *            充值交易id(可选)
	 * @param order_id
	 *            充值订单id(可选)
	 * @param order_no
	 *            充值订单号(可选)
	 * @param remark
	 *            备注(可选)
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
	public Result addBalance(Long user_id, Double amount, Integer balance_type, Date validity_time, Long transaction_id,
			Long order_id, String order_no, String remark) {
		Result r = new Result();
		synchronized (LOCK) {
			try {
				Balance b = new Balance();
				b.setAmount(amount);
				b.setCreate_time(new Date());
				b.setOrder_id(order_id);
				b.setOrder_no(order_no);
				b.setTransaction_id(transaction_id);
				b.setType(balance_type);
				String name = "";
				// 类型
				// 1.1个月58
				// 2.1个月158
				// 3.vip合作
				// 4.买次
				if (balance_type == 0) {
					name = "注册就送1000采集券|有效期1个月";
				} else if (balance_type == 1) {
					name = "58元套餐|有效期1个月";
				} else if (balance_type == 2) {
					name = "158元套餐|有效期1个月";
				} else if (balance_type == 3) {
					name = "VIP合作";
				} else if (balance_type == 4) {
					name = "买次|有效期1个月";
				}
				b.setName(name);

				b.setUpdate_time(new Date());
				b.setUser_id(user_id);

				if (validity_time == null) {
					Calendar c = Calendar.getInstance();
					c.add(Calendar.DAY_OF_YEAR, DEFUALT_VALIDITY_TIME);
					validity_time = c.getTime();
				}
				b.setValidity_time(validity_time);
				b.setRemark(remark);
				apiDao.fastInsert(b);

				BalanceBill bb = new BalanceBill();
				bb.setAmount(amount);
				bb.setBalance_id(b.getId());
				bb.setCreate_time(new Date());
				bb.setOrder_id(order_id);
				bb.setOrder_no(order_no);
				bb.setRemark(name);
				// 0:充值\r\n1:消费
				bb.setType(0);
				bb.setTransaction_id(transaction_id);
				bb.setUser_id(user_id);
				apiDao.fastInsert(bb);
				r.setResult(Const.SUC, "充值成功");
			} catch (Exception e) {
				logger.error("充值失败", e);
				r.setResult(Const.FAIL_500, "充值失败");
			}
		}
		return r;
	}

	/**
	 * 消耗智慧豆
	 * 
	 * @param user_id
	 * @param amount
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
	public Result subtractBalance(Long user_id, Double amount) {
		Result r = new Result();
		synchronized (LOCK) {
			try {
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY, 0);// 时
				cal.set(Calendar.MINUTE, 0);// 分
				cal.set(Calendar.SECOND, 0);// 秒
				Date d = cal.getTime();

				List<Balance> list = apiDao.query(Balance.class, Cnd.where("user_id", "=", user_id)
						.and("validity_time", ">=", d).asc("validity_time").asc("type"));
				Double sum = 0d;
				for (Balance b : list) {
					sum = sum + b.getAmount();
				}
				if (amount > sum) {
					r.setResult(Const.FAIL_500, "消费[" + amount + "]账户余额[" + sum + "]不足");
					return r;
				}
				sum = 0d;
				for (Balance b : list) {
					if (b.getAmount() >= amount - sum) {
						apiDao.update(Balance.class, Chain.make("amount", b.getAmount() - (amount - sum)),
								Cnd.where("id", "=", b.getId()));
						break;
					} else {
						sum = sum + b.getAmount();
						apiDao.update(Balance.class, Chain.make("amount", 0), Cnd.where("id", "=", b.getId()));
						break;
					}
				}
				Balance b = list.get(0);
				BalanceBill bb = new BalanceBill();
				bb.setAmount(amount);
				bb.setBalance_id(b.getId());
				bb.setCreate_time(new Date());
				bb.setRemark("用户[" + user_id + "]消耗[" + amount + "]个采集券");
				// 0:充值\r\n1:消费
				bb.setType(1);
				bb.setUser_id(user_id);
				apiDao.fastInsert(bb);
				JSONObject j = new JSONObject();
				j.put("amount", amount);

				
			} catch (Exception e) {
				logger.error("消费失败[" + user_id + "]", e);
				r.setResult(Const.FAIL_500, "账户异常");
			}
		}
		return r;
	}
	
	

}
