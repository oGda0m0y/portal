package com.zhishu.portal.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhishu.model.Project;
import com.zhishu.model.Result;
import com.zhishu.model.WorkOrder;
import com.zhishu.portal.common.result.Page;
import com.zhishu.portal.common.result.USession;
import com.zhishu.portal.service.CompVipService;
import com.zhishu.portal.service.MessageService;
import com.zhishu.portal.service.MyService;
import com.zhishu.portal.service.UserService;
import com.zhishu.portal.util.Const;

@Controller
@RequestMapping("/action/my/c")
public class CompAction extends BaseAction {
	private static Logger logger = Logger.getLogger(CompAction.class);
	@Resource
	private UserService userService;
	@Resource
	private MyService myService;
	@Resource
	private MessageService messageService;
	@Resource
	private CompVipService compVipService;

	@RequestMapping(value = "/toCompProject")
	public ModelAndView toCompProject(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		try {

			view.setViewName("/web/my/comp/comp_project.jsp");

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@RequestMapping(value = "/getCompProject")
	@ResponseBody
	public Page getCompProject(HttpServletRequest request, HttpServletResponse response, Integer pageNum,
			Integer pageSize) throws Exception {
		Page page = new Page();
		try {
			USession us = this.getUserSession(request);
			page = compVipService.getCompProject(us.getId(), pageNum, pageSize);
		} catch (Exception e) {
			page.setCode(Const.FAIL_500);
			logger.error("", e);
		}
		return page;
	}
	
	@RequestMapping(value = "/getCompWorkOrder")
	@ResponseBody
	public Page getCompWorkOrder(HttpServletRequest request, HttpServletResponse response, Integer pageNum,
			Integer pageSize) throws Exception {
		Page page = new Page();
		try {
			USession us = this.getUserSession(request);
			
			page = compVipService.getCompWorkOrder(us.getId(), pageNum, pageSize);
		} catch (Exception e) {
			page.setCode(Const.FAIL_500);
			logger.error("", e);
		}
		return page;
	}

	@RequestMapping(value = "/saveCompProject")
	@ResponseBody
	public Result saveCompProject(HttpServletRequest request, HttpServletResponse response, String pname, String website)
			throws Exception {
		Result ret = new Result();
		try {
			USession us = this.getUserSession(request);
			if (compVipService.saveCompProject(us.getId(), pname, website)) {
				ret.setResult(Const.SUC, "操作成功");
			} else {
				ret.setResult(Const.FAIL, "操作失败");
			}
		} catch (Exception e) {
			ret.setCode(Const.FAIL_500);
			logger.error("", e);
		}
		return ret;
	}

	@RequestMapping(value = "/toCompRecord")
	public ModelAndView toCompRecord(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		try {

			view.setViewName("/web/my/comp/comp_record.jsp");

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@RequestMapping(value = "/toCompWorkOrder")
	public ModelAndView toCompWorkOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		try {

			view.setViewName("/web/my/comp/comp_order.jsp");

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@RequestMapping(value = "/toCompSubWorkOrder")
	public ModelAndView toCompSubWorkOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		try {
			USession us = this.getUserSession(request);
			if (us != null) {
				List<Project> list = compVipService.getProject(us.getId());
				String lists = this.parseDateJson(list);
				view.addObject("p_list", lists);
			}
			view.setViewName("/web/my/comp/submit_order.jsp");

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@RequestMapping(value = "/saveWorkOrder")
	@ResponseBody
	public Result saveWorkOrder(HttpServletRequest request, HttpServletResponse response, WorkOrder workorder)
			throws Exception {
		Result ret = new Result();
		try {
			USession us = this.getUserSession(request);
			if (compVipService.saveWorkOrder(us.getId(), workorder)) {
				ret.setResult(Const.SUC, "操作成功");
			} else {
				ret.setResult(Const.FAIL, "操作失败");
			}
		} catch (Exception e) {
			ret.setCode(Const.FAIL_500);
			logger.error("", e);
		}
		return ret;
	}

}
