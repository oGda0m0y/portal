package com.bds.portal.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bds.api.IServiceApi;
import com.bds.model.MyAccount;
import com.bds.model.Result;
import com.bds.model.TRegin;
import com.bds.model.User;
import com.bds.portal.util.CheckUtil;
import com.bds.portal.util.Const;
import com.bds.portal.util.MD5Util;
import com.bds.portal.util.RedisUtil;
import com.bds.portal.util.SendMsgUtil;
import com.caucho.hessian.client.HessianProxyFactory;

@Component
public class UserService {

	private static Logger logger = Logger.getLogger(UserService.class);
	private static HessianProxyFactory factory = new HessianProxyFactory();
	@Resource
	private NutDao mysqlDao;

	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
	public Result regUser(User user) {

		Result ret = new Result();
		try {
			IServiceApi apiService = (IServiceApi) factory.create(IServiceApi.class, Const.TASK_API_URL);
			if (CheckUtil.checkEmail(user.getUser_name())) {
				user.setEmail(user.getEmail());
			}
			if (CheckUtil.checkMobileNumber(user.getUser_name())) {
				user.setMobile(user.getUser_name());
			}
			String pwd = MD5Util.getMD5(user.getPassword());
			user.setPassword(pwd);
			String rcode = RedisUtil.getString(user.getUser_name());

			if (StringUtils.isEmpty(rcode)) {
				ret.setResult(Const.FAIL, "验证码已经过期");
			} else {
				if (!rcode.equals(user.getCode())) {
					ret.setResult(Const.FAIL, "验证码不一致");
				} else {

					User u = mysqlDao.fetch(User.class, Cnd.where("user_name", "=", user.getUser_name()));
					if (u != null) {

						ret.setResult(Const.FAIL, "手机号或者邮箱已经存在");
						return ret;
					}
					user.setStatus(1);
					user.setCreate_time(new Date());
					user.setType(0);
					mysqlDao.fastInsert(user);
					Calendar cal = Calendar.getInstance();

					cal.set(Calendar.DAY_OF_MONTH, 30);
					Date endDate = cal.getTime();
					ret = apiService.addBalance(user.getId(), 1000d, 0, endDate, null, null, null, null);
					if (!ret.getCode().equals(200)) {
						throw new Exception("充值异常");
					}
					ret.setResult(Const.SUC, user);
				}
			}

		} catch (Exception e) {
			ret.setResult(Const.FAIL, "注册出错");
			logger.error("检查用户名密码出错", e);

		}
		return ret;
	}

	public Result login(String username, String password, String code) {
		Result ret = new Result();
		try {

			// String rcode = RedisUtil.getString(username);
			//
			// if (StringUtils.isEmpty(rcode)) {
			// ret.setResult(Const.FIAL, "验证码已经过期");
			// } else {
			// if (!rcode.equals(code)) {
			// ret.setResult(Const.FIAL, "验证码不一致");
			// } else {
			User user = mysqlDao.fetch(User.class,
					Cnd.where("user_name", "=", username).and("password", "=", password));
			if (user != null) {

				ret.setResult(Const.SUC, user);

			} else {
				ret.setResult(Const.FAIL, "用户名或密码错误");
			}
			// }
			//
			// }

		} catch (Exception e) {
			logger.info("", e);
		}
		return ret;
	}

	public User getUserInfo(Long id) {

		try {

			User user = mysqlDao.fetch(User.class, Cnd.where("id", "=", id));
			MyAccount account = mysqlDao.fetch(MyAccount.class, Cnd.where("user_id", "=", user.getId()));
			if (account != null) {
				BigDecimal sale = account.getSumsale();
				BigDecimal last = account.getLastday();
				BigDecimal amount = account.getAmount();
				if (sale != null) {
					double f1 = sale.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					account.setTotalSale(f1);

				} else {
					account.setTotalSale(0.0);
				}
				if (amount != null) {
					double totalAmount = amount.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					account.setTotalAmount(totalAmount);

				} else {
					account.setTotalAmount(0.0);
				}
				if (last != null) {
					double f1 = last.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					account.setLastSale(f1);

				} else {
					account.setLastSale(0.0);
				}
				user.setAccount(account);
			}

			return user;

		} catch (Exception e) {
			logger.info("", e);
		}
		return null;
	}

	public List<Record> getJobStatus(Long user_id) {
		String sql = "SELECT count(0) as value,case  when status=0 then '待采集' when status=1 then '采集中' when status=2 then '采集完成' when status=3 then '采集失败' when status=4 then '解析中' when status=5 then '解析完成' when status=6 then '解析失败' else '其他' end as name FROM t_task_page where user_id="
				+ user_id + " GROUP BY status";
		Sql tsql = Sqls.queryRecord(sql);
		tsql.setCallback(Sqls.callback.records());
		mysqlDao.execute(tsql);

		List<Record> list = tsql.getList(Record.class);
		return list;
	}

	public User upUserInfo(Long id, String company, String imgPath, String prv, String city, String area,
			String business) {

		try {

			User user = mysqlDao.fetch(User.class, Cnd.where("id", "=", id));
			user.setImg(imgPath);
			user.setCompany(company);
			user.setBusiness(business);
			if (StringUtils.isNotEmpty(prv)) {
				TRegin regin = mysqlDao.fetch(TRegin.class, Cnd.where("id", "=", prv));
				if (regin != null) {
					user.setPrv_code(regin.getId());
					user.setPrv_name(regin.getName());
				}

			}

			if (StringUtils.isNotEmpty(city)) {
				TRegin regin = mysqlDao.fetch(TRegin.class, Cnd.where("id", "=", city));
				if (regin != null) {
					user.setCity_code(regin.getId());
					user.setCity_name(regin.getName());
				}

			}

			if (StringUtils.isNotEmpty(area)) {
				TRegin regin = mysqlDao.fetch(TRegin.class, Cnd.where("id", "=", area));
				if (regin != null) {
					user.setArea_code(regin.getId());
					user.setArea_name(regin.getName());
				}

			}

			mysqlDao.updateIgnoreNull(user);
			return user;
		} catch (Exception e) {
			logger.info("", e);
			return null;
		}

	}

	public User upUserMobile(Long id, String newMobile) {

		try {

			User user = mysqlDao.fetch(User.class, Cnd.where("id", "=", id));
			user.setMobile(newMobile);
			mysqlDao.updateIgnoreNull(user);

			return user;
		} catch (Exception e) {
			logger.info("", e);
			return null;
		}

	}

	public User upUserEmail(Long id, String email) {

		try {

			User user = mysqlDao.fetch(User.class, Cnd.where("id", "=", id));
			user.setEmail(email);
			mysqlDao.updateIgnoreNull(user);

			return user;
		} catch (Exception e) {
			logger.info("", e);
			return null;
		}

	}

	public Result upUserPwd(Long id, String curPwd, String newPwd) {
		Result ret = new Result();
		try {

			User user = mysqlDao.fetch(User.class, Cnd.where("id", "=", id));

			if (StringUtils.isEmpty(curPwd)) {
				ret.setResult(Const.FAIL, "当前密码不能为空");
				return ret;
			}

			if (StringUtils.isEmpty(newPwd)) {

				ret.setResult(Const.FAIL, "新密码不能为空");
				return ret;
			}

			if (!curPwd.equals(user.getPassword())) {
				ret.setResult(Const.FAIL, "当前密码不正确");
				return ret;
			}

			mysqlDao.update(User.class, Chain.make("password", newPwd), Cnd.where("id", "=", id));
			ret.setResult(Const.SUC, "设置成功");
			return ret;
		} catch (Exception e) {
			logger.info("", e);
			ret.setResult(Const.FAIL, "操作失败");
			return ret;
		}

	}

	public Result sendCode(String username, String type) {
		Result ret = new Result();
		String code = "";
		if (type.equals("phone")) {
			try {
				code = SendMsgUtil.SendPhoneCode(username);
				RedisUtil.setexString(username, code, 1800);

				ret.setResult(Const.SUC, "发送成功");
			} catch (Exception e) {
				logger.error("", e);
				ret.setResult(Const.FAIL, "发送短信异常");
			}

		} else {
			try {
				code = SendMsgUtil.SendMailCode(username);
				RedisUtil.setexString(username, code, 1800);

				ret.setResult(Const.SUC, "发送成功");
			} catch (Exception e) {
				ret.setResult(Const.FAIL, "发送邮件异常");
			}
		}

		return ret;
	}

}
