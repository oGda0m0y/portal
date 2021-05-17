package com.zhishu.portal.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhishu.model.Result;
import com.zhishu.portal.common.result.USession;
import com.zhishu.portal.service.PortalService;
import com.zhishu.portal.service.TempletService;
import com.zhishu.portal.util.Const;

@Controller
@RequestMapping("/action/tmpl/t")
public class TempletAction {
	private static Logger logger = Logger.getLogger(TempletAction.class);
	@Resource
	private TempletService templetService;
	@Resource
	private PortalService portalService;

	@ResponseBody
	@RequestMapping(value = "/addTemplateDetail", method = RequestMethod.POST)
	public Result addTemplateDetail(HttpServletRequest request, HttpServletResponse response, String requestId,
			String columnName, String type, String thinkDataId) {
		Result ret = new Result();
		ret = templetService.addTemplateDetail(requestId, columnName, type, thinkDataId);
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		return ret;
	}

	@ResponseBody
	@RequestMapping(value = "/delTemplateDetail", method = RequestMethod.POST)
	public Result addTemplateDetail(HttpServletRequest request, HttpServletResponse response, String uniqueId) {
		Result ret = new Result();
		ret = templetService.delTemplateDetail(uniqueId);
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		return ret;
	}

	@ResponseBody
	@RequestMapping(value = "/updTemplateDetail", method = RequestMethod.POST)
	public Result updTemplateDetail(HttpServletRequest request, HttpServletResponse response, String newColumnName,
			String uniqueId) {
		Result ret = new Result();
		ret = templetService.updTemplateDetail(newColumnName, uniqueId);
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		return ret;
	}

	@ResponseBody
	@RequestMapping(value = "/saveTemplate", method = RequestMethod.POST)
	public Result addTemplate(HttpServletRequest request, HttpServletResponse response, String requestId, String title,
			String tmpl, Integer rows) {
		Result ret = new Result();
		JSONObject tpl = JSONObject.parseObject(tmpl);
		JSONArray arr = tpl.getJSONArray("arr");
		JSONArray array = new JSONArray();
		for (int i = 0; i < arr.size(); i++) {
			JSONObject item = arr.getJSONObject(i).getJSONObject("value");
			array.add(item);
		}

		// 需要验证用户是否登录
		// 若登录则取登录的用户id，否则，弹出登录框
		Object usession = request.getSession().getAttribute(Const.SESSION_KEY);

		if (usession == null) {
			ret.setCode(Const.FAIL);
			ret.setMsg("请登录");
			ret.setRet(false);
			return ret;
		} else {
			USession us = (USession) usession;
			requestId = requestId.substring(requestId.lastIndexOf("/") + 1);
			if (rows == null) {
				rows = 0;
			}
			ret = templetService.addTemplate(requestId, us.getId(), title, array, rows);

		}
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		return ret;
	}

	@ResponseBody
	@RequestMapping(value = "/getTemplateDetail", method = RequestMethod.POST)
	public Result getTemplateDetail(HttpServletRequest request, HttpServletResponse response, String requestId) {
		Result ret = new Result();
		ret = templetService.getTemplateDetail(requestId);
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		return ret;
	}

	/**
	@ResponseBody
	@RequestMapping(value = "/parseElePath", method = RequestMethod.POST)
	public Result parseElePath(HttpServletRequest request, HttpServletResponse response, String listPath,
			String nodePath, String requestId, String columnName, String columnType) {
		Result ret = new Result();
		ret = templetService.parseElePath(listPath, nodePath, requestId, columnName, columnType);
		return ret;
	}
	**/
}
