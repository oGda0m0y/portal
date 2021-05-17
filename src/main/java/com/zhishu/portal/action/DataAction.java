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

import com.zhishu.model.DataConfig;
import com.zhishu.model.Result;
import com.zhishu.model.Template;
import com.zhishu.portal.common.result.Page;
import com.zhishu.portal.common.result.USession;
import com.zhishu.portal.service.DataService;
import com.zhishu.portal.service.MyService;
import com.zhishu.portal.service.UserService;
import com.zhishu.portal.util.Const;

@Controller
@RequestMapping("/action/my")
public class DataAction extends BaseAction {
	private static Logger logger = Logger.getLogger(MyAction.class);
	@Resource
	private UserService userService;
	@Resource
	private DataService dataService;

	@Resource
	private MyService myService;

	@RequestMapping(value = "/m/toMyData")
	public ModelAndView toMyData(HttpServletRequest request, HttpServletResponse response, String requestid,
			Integer page) throws Exception {

		ModelAndView view = new ModelAndView();
		try {
			USession us = this.getUserSession(request);
			if (us != null) {

				List<Template> config = myService.getMyConfigList(us.getId());

				Page ret = myService.getPageData(us.getId(), requestid, page);
				String p = this.parseJson(ret);
				String jobs = this.parseDateJson(config);
				view.addObject("page", p);

				view.addObject("jobs", jobs);

				view.addObject("request_id", requestid);
			}
			view.setViewName("/web/my/data/my_data.jsp");

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@RequestMapping(value = "/m/getMyDagePage")
	@ResponseBody
	public Page getMyDagePage(HttpServletRequest request, HttpServletResponse response, String requestid,
			Integer page) {
		try {
			USession us = this.getUserSession(request);
			Page ret = myService.getPageData(us.getId(), requestid, page);
			return ret;
		} catch (Exception e) {
			logger.error("", e);
			return new Page();
		}

	}

	@RequestMapping(value = "/m/toMyExportDB")
	public ModelAndView toMyExportDB(HttpServletRequest request, HttpServletResponse response, String requestid,
			String type) {
		ModelAndView view = new ModelAndView();
		try {

			view.setViewName("/web/my/data/my_export.jsp");

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@RequestMapping(value = "/m/repMyDagePage")
	@ResponseBody
	public Result repMyDagePage(HttpServletRequest request, HttpServletResponse response, String requestid,
			Integer page) {
		Result ret = new Result();
		try {
			USession us = this.getUserSession(request);
			ret = myService.repPageData(us.getId(), requestid, page);
			return ret;
		} catch (Exception e) {
			logger.error("", e);
			ret.setResult(Const.FAIL, "下载异常");
			return ret;
		}

	}

	@RequestMapping(value = "/m/toMyDataConfig")
	public ModelAndView toMyDataConfig(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		try {
			USession us = this.getUserSession(request);
			if (us != null) {
				List<DataConfig> list = dataService.getDataConfig(us.getId());
				String lists = this.parseDateJson(list);
				view.addObject("list", lists);
			}
			view.setViewName("/web/my/data/mydata_config.jsp");

		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return view;
	}

	@RequestMapping(value = "/ds/addSaveDataSourse")
	@ResponseBody
	public Result addSaveDataSourse(HttpServletRequest request, HttpServletResponse response, String type, String host,
			String port, String username, String password, String sqldb) throws Exception {
		Result ret = new Result();
		USession us = this.getUserSession(request);
		if (us != null) {
			Boolean flag = dataService.addSaveDataSourse(type, host, port, username, password, sqldb, us.getId());
			ret.setResult(flag, "保存成功");
		} else {
			ret.setResult(false, "登陆Session失效，请重新登陆");
		}
		return ret;
	}

	@RequestMapping(value = "/myexp/getExportPage")
	@ResponseBody
	public Page getExportPage(HttpServletRequest request, HttpServletResponse response, Integer pageNum,
			Integer pageSize) throws Exception {
		Page page = new Page();
		try {
			USession us = this.getUserSession(request);
			page = dataService.getExportDataList(us.getId(), pageNum, pageSize);
		} catch (Exception e) {
			page.setCode(Const.FAIL_500);
			logger.error("", e);
		}
		return page;
	}

	@RequestMapping(value = "/ds/modifySaveDataSourse")
	@ResponseBody
	public Boolean modifySaveDataSourse(HttpServletRequest request, HttpServletResponse response, String type,
			String host, String port, String username, String password, String sqldb, String id) throws Exception {
		Boolean flag = dataService.modifySaveDataSourse(type, host, port, username, password, sqldb, id);
		return flag;
	}

	@RequestMapping(value = "/ds/delDataSourse")
	@ResponseBody
	public Result delDataSourse(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception {
		Result ret = new Result();

		if (dataService.delDataSource(id)) {
			ret.setResult(Const.SUC, "操作成功");
		} else {
			ret.setResult(Const.FAIL, "操作失败");
		}

		return ret;
	}

	@RequestMapping(value = "/ds/testConnection")
	@ResponseBody
	public Result testConnection(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception {
		Result ret = new Result();
		if (dataService.testConnection(id)) {
			ret.setResult(Const.SUC, "操作成功");
		} else {
			ret.setResult(Const.FAIL, "操作失败");
		}

		return ret;
	}

	@RequestMapping(value = "/m/saveTableBtn")
	@ResponseBody
	public Result saveTable(HttpServletRequest request, HttpServletResponse response, Long sqlUrlId, String name,
			String sel_requestId) throws Exception {
		Result ret = new Result();
		ret = dataService.saveTable(sqlUrlId, name, sel_requestId);
		return ret;
	}

	@RequestMapping(value = "/m/getRequestId")
	@ResponseBody
	public Result getrequestId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Result ret = new Result();
		USession us = this.getUserSession(request);
		List<DataConfig> dataConfig = dataService.getDataConfig(us.getId());
		if (null != dataConfig) {
			ret.setResult(Const.SUC, dataConfig);
		} else {
			ret.setResult(Const.FAIL, null);
		}
		return ret;
	}

	@RequestMapping(value = "/m/exportSource")
	@ResponseBody
	public void exportSql(HttpServletRequest request, HttpServletResponse response, String sql_id, String table_id,
			String sel_requestId) throws Exception {
		USession us = this.getUserSession(request);
		dataService.exportSource(us.getId(), sql_id, table_id, sel_requestId);
	}
}
