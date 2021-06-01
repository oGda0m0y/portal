package com.bds.portal.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bds.model.Result;
import com.bds.model.Task;
import com.bds.portal.service.PortalService;
import com.bds.portal.util.Const;
import com.bds.portal.util.MD5Util;

@Controller
@RequestMapping("/action/portal")
public class PortalAction {
	private static Logger logger = Logger.getLogger(PortalAction.class);
	@Resource
	private PortalService portalService;
	private SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");

	@ResponseBody
	@RequestMapping(value = "/posturl/{_zsuuid}", method = RequestMethod.POST)
	public ModelAndView submitUrl(HttpServletRequest request, HttpServletResponse response, String website,
			@PathVariable("_zsuuid") String _zsuuid) throws Exception {
		String sid = request.getSession().getId();

		String uuid = "";
		for (int i = 0; i < request.getCookies().length; i++) {
			if ("_zsuuid".equals(request.getCookies()[i].getName())) {
				uuid = request.getCookies()[i].getValue();
			}

		}
		String url = website;
		// Headers[] headers = request.getHeaders();

		ModelAndView view = new ModelAndView();
		try {
			// 调用api保存task，参数sid、url、cookie、uuid

			if (org.springframework.util.StringUtils.isEmpty(uuid)) {
				uuid = sid;
			}
			String now = fmt.format(new Date());

			String requestId = MD5Util.getMD5(uuid + url + now);

			Result ret = portalService.addTask(requestId, url, uuid, "");
			if (ret.isRet()) {

				view.addObject("uuid", uuid);// 参数传给前端
				view.addObject("requestId", requestId);
				view.setViewName("/portal/process/process.jsp");
				logger.info(requestId);
				logger.info(url);

				logger.info(uuid);
			} else {
				view.addObject("errorMsg", "采集异常，请重试或者联系客服人员");// 参数传给前端
				view.setViewName("/portal/error/500.html");
			}

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "/error/{requestId}")
	public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("requestId") String requestId) throws Exception {

		ModelAndView view = new ModelAndView();
		try {
			Task ret = portalService.getTask(requestId);
			view.addObject("requestId", requestId);
			if (ret.getStatus() == 2) {
				Result htmlRet = portalService.getHtml(requestId);
				if (htmlRet.getCode().equals(2)) {

					view.setViewName("/portal/process/process.jsp");
				} else {

					view.setViewName("/portal/error/404.html");
				}

			} else {
				view.setViewName("/portal/error/500.html");
			}

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "/rid/{requestId}")
	public ModelAndView forwardHtml(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("requestId") String requestId) throws Exception {

		ModelAndView view = new ModelAndView();
		try {
			Task ret = portalService.getTask(requestId);
			view.addObject("requestId", requestId);
			view.addObject("rows", ret.getRows());
			view.addObject("domainName", Const.Host_Ip + ":" + Const.Host_Port);
			if (ret.getStatus() == 2) {

				view.addObject("remote_url", "/action/portal/rid/html/" + requestId);
				view.setViewName("/web/main.jsp");

			} else {
				view.setViewName("/portal/error/500.html");
			}

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "/rid/html/{requestId}")
	public String getHtml(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("requestId") String requestId) throws Exception {

		try {

			Result htmlRet = portalService.getHtml(requestId);
			// FileWrite.wirteFile("D://test.html",
			// htmlRet.getData().toString());
			// logger.info( htmlRet.getData().toString());
			return htmlRet.getData().toString();

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/synHtml")
	public Result synHtml(HttpServletRequest request, HttpServletResponse response, String uuid, String requestId)
			throws Exception {
		Result ret = new Result();
		try {
			Task task = portalService.getTask(requestId);
			if (task != null) {
				ret.setData(task.getStatus());
				ret.setCode(Const.SUC);
			} else {
				ret.setResult(Const.FAIL_400, "服务器异常");
			}

		} catch (Exception e) {
			logger.error("", e);
			ret.setResult(Const.FAIL_400, "服务器异常");
		}
		return ret;
	}

}
