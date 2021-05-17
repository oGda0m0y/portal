package com.zhishu.portal.service;

import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.cri.SimpleCriteria;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.caucho.hessian.client.HessianProxyFactory;
import com.zhishu.api.IServiceApi;
import com.zhishu.model.Order;
import com.zhishu.model.Result;
import com.zhishu.model.Transaction;
import com.zhishu.portal.common.result.Page;
import com.zhishu.portal.util.Const;

/**
 * 充值消费记录
 * 
 * @author Administrator
 *
 */
@Component
public class RechargeService {

	private static Logger logger = Logger.getLogger(RechargeService.class);
	private static HessianProxyFactory factory = new HessianProxyFactory();
	@Resource
	private NutDao mysqlDao;

	/**
	 * 
	 * @param user_id
	 * @param begin
	 * @param end
	 * @param type
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page queryList(Long user_id, Date begin, Date end, Integer type, Integer pageNum, Integer pageSize) {
		Page p = new Page();
		Sql sql = Sqls.queryRecord("SELECT * FROM t_balance_bill $condition");
		Sql sqlCount = Sqls.fetchInt("SELECT count(0) FROM t_balance_bill $condition");
		SimpleCriteria cri = Cnd.cri();
		cri.where().and("user_id", "=", user_id);
		if (begin != null) {
			cri.where().and("create_time", ">=", begin);
		}
		if (end != null) {
			cri.where().and("create_time", "<=", end);
		}
		if (type != null) {
			cri.where().and("type", "=", type);
		}

		Pager pager = mysqlDao.createPager(pageNum, pageSize);
		sqlCount.setCondition(cri);
		pager.setRecordCount(mysqlDao.execute(sqlCount).getInt());
		cri.desc("id");

		sql.setCondition(cri);
		sql.setPager(pager);
		sql.setCallback(Sqls.callback.records());
		mysqlDao.execute(sql);
		List<Record> retList = sql.getList(Record.class);
		p = new Page(pager.getRecordCount(), pager.getPageNumber(), pager.getPageSize());
		p.setData(retList);
		p.setCode(Const.SUC);
		p.setMsg("操作成功.");

		return p;
	}

	/**
	 * 创建订单
	 * 
	 * @param order
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result createOrder(Order order) {
		Result r = new Result();
		mysqlDao.insert(order);
		r.setCode(Const.SUC);
		r.setMsg("操作成功.");
		r.setData(order);
		return r;
	}

	public Order getOrderDetail(Long userId, String order_no, String order_id) {
		Order r = null;
		if (StringUtils.isEmpty(order_id) && StringUtils.isEmpty(order_no)) {
			return r;
		}
		if (StringUtils.isNotEmpty(order_no)) {
			r = mysqlDao.fetch(Order.class, Cnd.where("user_id", "=", userId).and("order_no", "=", order_no));
			return r;
		}
		if (r == null && StringUtils.isNotEmpty(order_id)) {
			r = mysqlDao.fetch(Order.class, Cnd.where("user_id", "=", userId).and("id", "=", order_id));
		}
		return r;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Result trade(String order_no, String trade_no, String trade_status, String trade_type) {
		IServiceApi apiService;
		Result r = new Result();
		try {
			apiService = (IServiceApi) factory.create(IServiceApi.class, Const.TASK_API_URL);
		} catch (MalformedURLException e1) {
			logger.error("", e1);
			r.setResult(Const.FAIL_400, "获取支付接口异常");
			return r;
		}
		if (trade_status.equals("TRADE_SUCCESS")) {
			// 支付成功
			mysqlDao.update(Order.class,
					Chain.make("order_status", 2).add("trade_time", new Date()).add("trade_no", trade_no)
							.add("trade_status", trade_status).add("trade_type", trade_type),
					Cnd.where("order_no", "=", order_no));

			Order order = mysqlDao.fetch(Order.class, Cnd.where("order_no", "=", order_no));
			try {
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE, 30);
				Date validity_time = c.getTime();
				Transaction t = new Transaction();
				t.setAmount(order.getAmount());
				t.setCreate_time(new Date());
				t.setOrder_id(order.getId());
				t.setOrder_no(order_no);
				t.setPay_type(1);
				t.setRemark(order.getBody());
				t.setTransaction_status(3);
				t.setUser_id(order.getUser_id());
				t.setTrade_no(order.getTrade_no());
				mysqlDao.insert(t);
				apiService.addBalance(order.getUser_id(), order.getAmount(), order.getType(), validity_time, t.getId(),
						order.getId(), order_no, order.getBody());
			} catch (Exception e) {
				logger.error("", e);
				r.setResult(Const.FAIL_500, "支付操作异常");
				return r;
			}
		} else {
			// 支付失败
			mysqlDao.update(Order.class,
					Chain.make("order_status", 2).add("trade_time", new Date()).add("trade_no", trade_no)
							.add("trade_status", trade_status).add("trade_type", trade_type),
					Cnd.where("order_no", "=", order_no));
		}

		r.setCode(Const.SUC);
		r.setMsg("操作成功.");
		return r;
	}

}
