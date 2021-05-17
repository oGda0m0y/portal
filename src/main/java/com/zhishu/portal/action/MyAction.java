package com.zhishu.portal.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.nutz.dao.entity.Record;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhishu.model.User;
import com.zhishu.portal.common.result.Page;
import com.zhishu.portal.common.result.USession;
import com.zhishu.portal.service.MessageService;
import com.zhishu.portal.service.MyService;
import com.zhishu.portal.service.UserService;
import com.zhishu.portal.util.Const;

@Controller
@RequestMapping("/action/my")
public class MyAction extends BaseAction {
	private static Logger logger = Logger.getLogger(MyAction.class);
	@Resource
	private UserService userService;
	@Resource
	private MyService myService;
	@Resource
	private MessageService messageService;

	@RequestMapping(value = "/m/toMyCenter")
	public ModelAndView toMyCenter(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		try {
			USession us = this.getUserSession(request);
			User user = userService.getUserInfo(us.getId());

			List<Record> ts = userService.getJobStatus(us.getId());
			view.addObject("user", user);
			view.addObject("ts", ts);
			view.setViewName("/web/my/my_center.jsp");

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@RequestMapping(value = "/m/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		try {
			request.getSession().removeAttribute(Const.SESSION_KEY);

			view.setViewName("redirect:/");

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@RequestMapping(value = "/m/getMessagePage")
	@ResponseBody
	public Page getMessagePage(HttpServletRequest request, HttpServletResponse response, Integer pageNum,
			Integer pageSize, Integer isread) throws Exception {
		Page page = new Page();
		try {
			USession us = this.getUserSession(request);

			page = messageService.getMessageList(us.getId(), pageNum, pageSize, isread);
		} catch (Exception e) {
			page.setCode(Const.FAIL_500);
			logger.error("", e);
		}
		return page;
	}

	@RequestMapping(value = "/m/messageList")
	public ModelAndView messageList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		try {

			view.setViewName("/web/my/msg/message.jsp");

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@RequestMapping(value = "/m/readMsg")
	public ModelAndView readMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		try {

			messageService.redMessage();
			view.setViewName("/web/my/msg/message.jsp");

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

}
