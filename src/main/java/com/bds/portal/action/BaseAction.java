
package com.bds.portal.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bds.model.Result;
import com.bds.portal.common.result.USession;
import com.bds.portal.util.Const;

/**
 * @Title: BaseCtrl.java
 * @Package com.common.action
 * @author robin
 * @version V1.0
 */
public abstract class BaseAction {
	private static Logger logger = Logger.getLogger(BaseAction.class);


	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}

	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		return request;
	}

	public void writeJson(HttpServletResponse response, Object obj) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.print(JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue,
					SerializerFeature.WriteNullStringAsEmpty));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("转换json异常", e);
			Result ret = new Result();
			ret.setResult(Const.FAIL, "异常");
			this.writeJson(response, ret);
		}
	}

	public String parseJson(Object obj) {
		String json = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullStringAsEmpty);
		return json;
	}

	public String parseDateJson(Object obj) {
		String json = JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd", SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
		return json;
	}

	public String parseTimeJson(Object obj) {
		String json = JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
		return json;
	}

	public USession getUserSession(HttpServletRequest request) {
		USession us = null;
		Object session = request.getSession().getAttribute(Const.SESSION_KEY);
		if (session != null) {
			us = (USession) session;
			return us;
		} else
			return null;

	}
}
