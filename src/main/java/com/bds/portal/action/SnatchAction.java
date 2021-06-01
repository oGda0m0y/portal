package com.bds.portal.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bds.model.Result;
import com.bds.model.Template;
import com.bds.portal.common.result.USession;
import com.bds.portal.service.MyService;
import com.bds.portal.service.UserService;
import com.bds.portal.util.Const;

import shaded.org.apache.commons.lang3.StringUtils;

@Controller
@RequestMapping("/action/my")
public class SnatchAction extends BaseAction {
	private static Logger logger = Logger.getLogger(MyAction.class);
	@Resource
	private UserService userService;
	@Resource
	private MyService myService;
	private static DateFormat FORMAT1 = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat FORMAT2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@RequestMapping(value = "/m/toTemplateConfig")
	public ModelAndView toTemplateConfig(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		try {
			USession us = this.getUserSession(request);
			if (us != null) {
				List<Template> list = myService.getMyTemplateList(us.getId());
				String json = this.parseDateJson(list);
				view.addObject("detailList", json);
			}
			view.setViewName("/web/my/snatch/template.jsp");

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@RequestMapping(value = "/m/toMyJob")
	public ModelAndView toMyJob(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		try {
			USession us = this.getUserSession(request);
			if (us != null) {
				List<Template> list = myService.getMyTemplateList(us.getId());

				String json = this.parseDateJson(list);
				view.addObject("tmpList", json);
			}
			view.setViewName("/web/my/snatch/my_job.jsp");

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@RequestMapping(value = "/m/toMySnatch")
	public ModelAndView toMySnatch(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		try {
			USession us = this.getUserSession(request);
			if (us != null) {
				List<Template> list = myService.getMyTemplateList(us.getId());
				String json = this.parseDateJson(list);
				view.addObject("tmpList", json);
			}
			view.setViewName("/web/my/snatch/my_snatch.jsp");

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@RequestMapping(value = "/m/saveTemplateConfig")
	@ResponseBody
	public Result saveTemplateConfig(HttpServletRequest request, HttpServletResponse response, String tid,
			String template_name, String page_rule, String task_cycle, Integer minPage, Integer maxPage, String byType,
			Integer ispage, String start_date, String end_date) {
		Result ret = new Result();
		Date sdate = null;
		Date edate = null;
		try {
			if (byType.equals("now")) {

			} else if (byType.equals("once")) {
				try {
					sdate = FORMAT2.parse(start_date);
				} catch (Exception e) {
					ret.setResult(Const.FAIL, "开始日期格式不对");
					return ret;
				}
			} else {
				try {
					sdate = FORMAT1.parse(start_date);
				} catch (Exception e) {
					ret.setResult(Const.FAIL, "开始日期格式不对");
					return ret;
				}

				try {
					edate = FORMAT1.parse(end_date);
				} catch (Exception e) {
					ret.setResult(Const.FAIL, "结束日期格式不对");
					return ret;
				}
			}

			if (ispage != null && ispage == 1) {
				if (StringUtils.isNotEmpty(page_rule)) {
					int index = page_rule.indexOf("[[");
					int last = page_rule.indexOf("]]");

					if (index >= 0 && last >= 0) {
						if (myService.saveTemplateConfig(tid, template_name, page_rule, task_cycle, minPage, maxPage,
								byType, ispage, sdate, edate)) {
							ret.setResult(Const.SUC, "操作成功");
						} else {
							ret.setResult(Const.FAIL, "保存失败");
						}
					}else {
						ret.setResult(Const.FAIL, "翻页规则配置错误");
					}
				}else {
					ret.setResult(Const.FAIL, "没有配置翻页规则");
				}

			} else {
				if (myService.saveTemplateConfig(tid, template_name, page_rule, task_cycle, minPage, maxPage, byType,
						ispage, sdate, edate)) {
					ret.setResult(Const.SUC, "操作成功");
				} else {
					ret.setResult(Const.FAIL, "保存失败");
				}
			}

		} catch (Exception e) {
			ret.setResult(Const.FAIL, "保存异常");
		}

		return ret;
	}

	@RequestMapping(value = "/m/startTmplJob")
	@ResponseBody
	public Result startTmplJob(HttpServletRequest request, HttpServletResponse response, String tid) {
		Result ret = new Result();
		try {
			ret = myService.doStartJob(tid);
		} catch (Exception e) {
			logger.error("", e);
			ret.setResult(Const.FAIL, "启动任务失败");
		}
		return ret;
	}
}
