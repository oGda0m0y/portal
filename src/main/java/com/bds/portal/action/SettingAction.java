package com.bds.portal.action;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.postgresql.util.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.bds.model.Result;
import com.bds.model.TRegin;
import com.bds.model.User;
import com.bds.portal.common.result.USession;
import com.bds.portal.service.MyService;
import com.bds.portal.service.UserService;
import com.bds.portal.util.Const;
import com.bds.portal.util.IMGUploadOSSUtil;
import com.bds.portal.util.RedisUtil;

import shaded.org.apache.commons.lang3.StringUtils;

@Controller
@RequestMapping("/action/my")
public class SettingAction extends BaseAction {
	private static Logger logger = Logger.getLogger(SettingAction.class);
	@Resource
	private UserService userService;
	@Resource
	private MyService myService;

	@RequestMapping(value = "/s/accountSetting")
	public ModelAndView accontSettings(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		try {
			List<TRegin> list = myService.getReginByLevel(1);
			Long id = this.getUserSession(request).getId();
			User u = userService.getUserInfo(id);

			String rlist = JSONObject.toJSONString(list);
			// pd.put("prvlist", rlist);
			view.addObject("prvlist", rlist);
			view.addObject("user", u);
			// mv.addAllObjects(map);
			view.setViewName("/web/my/settings/account.jsp");

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@RequestMapping(value = "/s/safetySetting")
	public ModelAndView satetySettings(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		try {

			view.setViewName("/web/my/settings/safety.jsp");

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "/s/getRegionByPid", method = RequestMethod.GET)
	public Result getRegionByPid(HttpServletRequest request, HttpServletResponse response, String parentCode)
			throws Exception {
		Result ret = new Result();

		try {
			List<TRegin> list = myService.getReginByPid(parentCode);
			ret.setCode(Const.SUC);
			ret.setMsg("??????????????????");
			ret.setData(list);
			return ret;
		} catch (Exception e) {
			ret.setResult(Const.FAIL, "????????????????????????");
			logger.error(e.getMessage(), new Throwable(e));
			return ret;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/s/upUserinfo", method = RequestMethod.GET)
	public Result upUserinfo(HttpServletRequest request, HttpServletResponse response, String company, String imgPath,
			String prv, String city, String area, String business) throws Exception {
		Result ret = new Result();

		try {

			User u = userService.upUserInfo(this.getUserSession(request).getId(), company, imgPath, prv, city, area,
					business);
			if (u != null) {
				USession us = this.getUserSession(request);
				us.setImg(u.getImg());
				request.getSession().setAttribute(Const.SESSION_KEY, us);
				ret.setCode(Const.SUC);
				ret.setMsg("??????????????????");
			} else {
				ret.setResult(Const.FAIL, "??????????????????");
			}

			return ret;
		} catch (

		Exception e) {
			ret.setResult(Const.FAIL, "??????????????????");
			logger.error(e.getMessage(), new Throwable(e));
			return ret;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/s/upPwd", method = RequestMethod.POST)
	public Result upPwd(HttpServletRequest request, HttpServletResponse response, String curPwd, String newPwd)
			throws Exception {
		Result ret = new Result();

		try {
			USession us = this.getUserSession(request);
			ret = userService.upUserPwd(us.getId(), curPwd, newPwd);

			return ret;
		} catch (Exception e) {
			ret.setResult(Const.FAIL, "??????????????????");
			logger.error(e.getMessage(), new Throwable(e));
			return ret;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/s/upUserEmail", method = RequestMethod.POST)
	public Result upUserEmail(HttpServletRequest request, HttpServletResponse response, String curEmail, String code)
			throws Exception {
		Result ret = new Result();

		try {
			USession us = this.getUserSession(request);
			if (StringUtils.isEmpty(curEmail)) {
				ret.setResult(Const.FAIL, "??????????????????");
				return ret;
			}

			if (StringUtils.isEmpty(code)) {
				ret.setResult(Const.FAIL, "???????????????????????????");
				return ret;
			}

			if (Const.checkEmail(curEmail)) {
				String rcode = RedisUtil.getString(curEmail);
				if (StringUtils.isEmpty(rcode)) {
					ret.setResult(Const.FAIL, "?????????????????????");
					return ret;
				} else {
					if (code.equals(rcode)) {
						User u = userService.upUserEmail(us.getId(), curEmail);
						if (u != null) {
							us.setEmail(curEmail);
							request.getSession().setAttribute(Const.SESSION_KEY, us);
							ret.setCode(Const.SUC);
							ret.setMsg("??????????????????");
						} else {
							ret.setResult(Const.FAIL, "??????????????????");
						}
					} else {
						ret.setResult(Const.FAIL, "????????????????????????");
						return ret;
					}
				}
			} else {
				ret.setResult(Const.FAIL, "??????????????????");
				return ret;
			}

			return ret;
		} catch (Exception e) {
			ret.setResult(Const.FAIL, "??????????????????");
			logger.error(e.getMessage(), new Throwable(e));
			return ret;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/s/upNewMobile", method = RequestMethod.POST)
	public Result upNewMobile(HttpServletRequest request, HttpServletResponse response, String newMobile,
			String newMobileCode) throws Exception {
		Result ret = new Result();

		try {
			USession us = this.getUserSession(request);
			if (StringUtils.isEmpty(newMobile)) {
				ret.setResult(Const.FAIL, "????????????????????????");
				return ret;
			}

			if (StringUtils.isEmpty(newMobileCode)) {
				ret.setResult(Const.FAIL, "?????????????????????????????????");
				return ret;
			}

			if (us.getMobile().equals(newMobile)) {
				ret.setResult(Const.FAIL, "???????????????????????????????????????");
				return ret;
			}

			if (Const.isPhoneLegal(newMobile)) {
				String rcode = RedisUtil.getString(newMobile);
				if (StringUtils.isEmpty(rcode)) {
					ret.setResult(Const.FAIL, "?????????????????????");
					return ret;
				} else {
					if (newMobileCode.equals(rcode)) {
						User u = userService.upUserMobile(us.getId(), newMobile);
						if (u != null) {
							us.setMobile(u.getMobile());
							request.getSession().setAttribute(Const.SESSION_KEY, us);
							ret.setCode(Const.SUC);
							ret.setMsg("??????????????????");
						} else {
							ret.setResult(Const.FAIL, "??????????????????");
						}
					} else {
						ret.setResult(Const.FAIL, "????????????????????????");
						return ret;
					}
				}
			} else {
				ret.setResult(Const.FAIL, "???????????????????????????");
				return ret;
			}

			return ret;
		} catch (Exception e) {
			ret.setResult(Const.FAIL, "?????????????????????");
			logger.error(e.getMessage(), new Throwable(e));
			return ret;
		}

	}

	@RequestMapping(value = "/s/uploadImage", method = RequestMethod.POST)
	@ResponseBody
	public Result uploadImgPic(HttpServletRequest httpRequest, HttpServletResponse response, String image)
			throws Exception {
		Result ret = new Result();
		response.setContentType("text/html;charset=utf-8");
		try {
			String heads = image.split(",")[0];

			String fname = heads.split(";")[0].split("/")[1];

			String base64Img = image.split(",")[1];

			byte[] imgs = Base64.decode(base64Img);
			String url = IMGUploadOSSUtil.uploadFile(UUID.randomUUID().toString() + "." + fname, imgs);

			ret.setCode(Const.SUC);
			ret.setData(url);

			return ret;
		} catch (Exception e) {
			ret.setResult(Const.FAIL, "??????????????????");

			return ret;
		}

	}
}
