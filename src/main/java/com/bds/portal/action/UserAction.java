package com.bds.portal.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bds.model.Result;
import com.bds.model.User;
import com.bds.portal.common.result.USession;
import com.bds.portal.service.UserService;
import com.bds.portal.util.Const;
import com.bds.portal.util.RedisUtil;

@Controller
@RequestMapping("/action/user")
public class UserAction extends BaseAction {
	private static Logger logger = Logger.getLogger(UserAction.class);
	@Resource
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/u/login", method = RequestMethod.POST)
	public Result login(HttpServletRequest request, HttpServletResponse response, String username, String password) {
		Result ret = new Result();
		try {
			ret = userService.login(username, password, null);
			if (ret.getCode().equals(200)) {
				USession us = new USession();
				User u = (User) ret.getData();
				us.setId(u.getId());
				us.setUser_name(u.getUser_name());
				us.setImg(u.getImg());
				us.setMobile(u.getMobile());
				us.setEmail(u.getEmail());
				us.setType(u.getType());
				request.getSession().setAttribute(Const.SESSION_KEY, us);
			}
		} catch (Exception e) {
			logger.error("", e);
		}

		return ret;

	}

	@ResponseBody
	@RequestMapping(value = "/u/compLogin", method = RequestMethod.POST)
	public Result compLogin(HttpServletRequest request, HttpServletResponse response, String username,
			String password) {
		Result ret = new Result();
		try {
			ret = userService.login(username, password, null);
			if (ret.getCode().equals(200)) {
				User u = (User) ret.getData();
				if (u.getType().equals(1)) {
					USession us = new USession();

					us.setId(u.getId());
					us.setUser_name(u.getUser_name());
					us.setImg(u.getImg());
					us.setMobile(u.getMobile());
					us.setEmail(u.getEmail());
					us.setType(u.getType());
					request.getSession().setAttribute(Const.SESSION_KEY, us);
				} else {
					ret.setResult(Const.FAIL, "您不是VIP用户不能在此登陆");
				}

			}
		} catch (Exception e) {
			logger.error("", e);
			ret.setResult(Const.FAIL, "登陆异常");
		}

		return ret;

	}

	@ResponseBody
	@RequestMapping(value = "/u/islogin", method = RequestMethod.POST)
	public Result isLogin(HttpServletRequest request, HttpServletResponse response, String username, String password) {
		Result ret = new Result();
		try {
			Object session = request.getSession().getAttribute(Const.SESSION_KEY);
			if (session != null) {
				USession us = (USession) session;
				ret.setResult(Const.SUC, us);
			} else {
				ret.setResult(Const.FAIL, "没有登录");
			}
		} catch (Exception e) {
			logger.error("", e);
			ret.setResult(Const.FAIL, "没有登录");
		}

		return ret;

	}

	@ResponseBody
	@RequestMapping(value = "/u/regUser", method = RequestMethod.POST)
	public Result regUser(HttpServletRequest request, HttpServletResponse response, User user) {
		Result ret = new Result();
		try {
			ret = userService.regUser(user);
			if (ret.getCode().equals(200)) {
				USession us = new USession();
				User u = (User) ret.getData();
				us.setId(u.getId());
				us.setUser_name(u.getUser_name());
				us.setImg(u.getImg());
				us.setMobile(u.getMobile());
				us.setEmail(u.getEmail());
				us.setType(u.getType());
				request.getSession().setAttribute(Const.SESSION_KEY, us);
			}
		} catch (Exception e) {
			logger.error("", e);
		}

		return ret;

	}

	@ResponseBody
	@RequestMapping(value = "/u/checkCode", method = RequestMethod.POST)
	public Result checkCode(HttpServletRequest request, HttpServletResponse response, String mobile, String code) {
		Result ret = new Result();
		try {
			if (StringUtils.isEmpty(mobile)) {
				ret.setResult(Const.FAIL, "手机号码不能为空");
				return ret;
			}
			String rcode = RedisUtil.getString(mobile);
			if (StringUtils.isEmpty(rcode)) {
				ret.setResult(Const.FAIL, "验证码已经过期");
				return ret;
			}

			if (StringUtils.isEmpty(code)) {
				ret.setResult(Const.FAIL, "验证码不能为空");
				return ret;
			}

			USession us = this.getUserSession(request);

			if (!us.getMobile().equals(mobile)) {
				ret.setResult(Const.FAIL, "输入的手机号与当前手机号不匹配");
				return ret;
			}

			if (Const.isPhoneLegal(mobile)) {
				if (code.equals(rcode)) {
					ret.setResult(Const.SUC, "校验成功");
				} else {
					ret.setResult(Const.FAIL, "输入的验证码不正确");
					return ret;
				}
			} else {
				ret.setResult(Const.FAIL, "输入的手机号格式不对");
				return ret;
			}

		} catch (Exception e) {
			logger.error("", e);
			ret.setResult(Const.FAIL, "输入的验证码不正确");
		}

		return ret;

	}

	@ResponseBody
	@RequestMapping(value = "/u/sendCode", method = RequestMethod.POST)
	public Result sendCode(HttpServletRequest request, HttpServletResponse response, String mobile, String type) {
		Result ret = new Result();
		try {
			ret = userService.sendCode(mobile, type);
		} catch (Exception e) {
			logger.error("", e);
		}
		return ret;

	}

}
