package com.zhishu.portal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.zookeeper.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/action")
public class IndexAction {
	private static Logger logger = Logger.getLogger(IndexAction.class);

	@ResponseBody
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {

			mv.setViewName("/index.html");

		} catch (Exception e) {
			logger.error(e.getMessage(), new Throwable(e));
			return null;
		}
		return mv;
	}
}
