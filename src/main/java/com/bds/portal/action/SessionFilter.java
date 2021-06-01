package com.bds.portal.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bds.model.Result;
import com.bds.portal.common.result.USession;
import com.bds.portal.util.Const;

public class SessionFilter extends HttpServlet implements Filter {

	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SessionFilter.class);
	private static final long serialVersionUID = 1L;
	/** 白名单_URL */
	private static String[] WHITE_LIST;
	private static String[] RESOURCES;

	@Override
	public void doFilter(ServletRequest serRequest, ServletResponse serRespone, FilterChain filter)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) serRequest;
		HttpServletResponse response = (HttpServletResponse) serRespone;
		HttpSession session = request.getSession(true);
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
	
		if (ctx != null && !ctx.equals("") && !ctx.equals("/")) {
			uri = uri.replaceAll(ctx, "");
		}

		// String[] indexs = uri.split("/");
		String requestType = request.getHeader("X-Requested-With");

		try {
			USession userSession = (USession) session.getAttribute(Const.SESSION_KEY);
			if (uri.equals("/") || containUrl(uri) || this.containResource(uri)) {
				filter.doFilter(serRequest, serRespone);
			} else {

				if (userSession == null) {
					if (this.isAjax(requestType)) {
						Result ret = new Result();
						ret.setResult(Const.FAIL, "没有登录请登录");
						this.writeJson(response, ret);
						return;
					} else {
						response.setStatus(401);
						response.setContentType("text/html;charset=utf-8");
						response.sendRedirect("/portal/user/login.html");
						return;
					}

				} else {
					filter.doFilter(serRequest, serRespone);
					return;

				}

			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return;
	}

	private boolean containUrl(String url) {
		boolean flag = false;
		for (String s : WHITE_LIST) {
			if (url.indexOf(s) >= 0) {
				if (s.equals(url)) {
					flag = true;
					break;
				} else  {
					url = url.substring(0, url.lastIndexOf("/")+1);
					
					if (s.equals(url)) {
						flag = true;
						break;
					}
				}

			}
		}
		return flag;
	}

	private boolean containResource(String url) {
		boolean flag = false;
		for (String s : RESOURCES) {
			if (url.contains(s)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	private boolean isAjax(String type) {
		if (type != null) {
			if (type.equalsIgnoreCase("XMLHttpRequest")) {
				return true;
			} else
				return false;
		}
		return false;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		WHITE_LIST = config.getInitParameter("WHITE_LIST").split(",");
		/** 前缀 */
		RESOURCES = config.getInitParameter("RESOURCES").split(",");
		/** 管理员角色 */

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
}
