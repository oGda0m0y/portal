package com.zhishu.portal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.caucho.hessian.client.HessianProxyFactory;
import com.zhishu.api.IServiceApi;
import com.zhishu.model.HeadView;
import com.zhishu.model.Result;
import com.zhishu.model.TRegin;
import com.zhishu.model.TaskPage;
import com.zhishu.model.TaskPageParse;
import com.zhishu.model.Template;
import com.zhishu.portal.common.result.Page;
import com.zhishu.portal.queue.HttpMQProducer;
import com.zhishu.portal.util.Const;

@Component
public class MyService {

	private static Logger logger = Logger.getLogger(MyService.class);
	private static HessianProxyFactory factory = new HessianProxyFactory();
	@Resource
	private NutDao mysqlDao;

	public List<TRegin> getReginByLevel(int level) {
		List<TRegin> list = mysqlDao.query(TRegin.class, Cnd.where("leveltype", "=", level));
		return list;
	}

	public Page getPageData(Long user_id, String request_id, Integer index) {

		Page ret = new Page();
		try {
			TaskPage job = mysqlDao.fetch(TaskPage.class,
					Cnd.where("request_id", "=", request_id).and("page", "=", index));
			if (job == null) {
				return ret;
			}
			String requestid = job.getRequest_id();
			int count = mysqlDao.count(TaskPage.class, Cnd.where("request_id", "=", requestid));
			TaskPageParse page = mysqlDao.fetch(TaskPageParse.class,
					Cnd.where("tpid", "=", job.getId()).and("user_id", "=", user_id));
			if (page != null) {
				JSONArray array = JSONObject.parseArray(page.getResult());
				ret = new Page(count, job.getPage(), array.size());
				ret.setMaxPage(count);
				ret.setPage(job.getPage());

				List<HeadView> head = new ArrayList<HeadView>();
				String[] ts = page.getHead().split(",");
				String[] titles = page.getTitle().split(",");
				for (int i = 0; i < ts.length; i++) {
					String prop = ts[i];
					HeadView h = new HeadView();
					h.setProp(prop);
					h.setTitle(titles[i]);

					head.add(h);
				}
				ret.setInfo(head);

				ret.setData(array);
				ret.setCode(Const.SUC);
			} else {
				ret.setData(new JSONArray());
				ret.setCode(Const.SUC);
			}

			return ret;
		} catch (Exception e) {
			logger.info("", e);
			ret.setCode(Const.FAIL);

		}
		return ret;
	}

	public Result repPageData(Long user_id, String request_id, Integer index) {

		Result ret = new Result();
		try {
			IServiceApi apiService = (IServiceApi) factory.create(IServiceApi.class, Const.TASK_API_URL);
			TaskPage job = mysqlDao.fetch(TaskPage.class,
					Cnd.where("request_id", "=", request_id).and("page", "=", index));
			if (job == null) {
				ret.setResult(Const.FAIL, "没有此任务");
				return ret;
			}

			mysqlDao.update(TaskPage.class, Chain.make("status", 0), Cnd.where("id", "=", job.getId()));
			ret = apiService.doJob(job.getPage_url(), job.getId(), job.getJob_id(), user_id);

			return ret;
		} catch (Exception e) {
			logger.info("", e);
			ret.setCode(Const.FAIL);

		}
		return ret;
	}

	public List<TRegin> getReginByPid(String pid) {
		List<TRegin> list = mysqlDao.query(TRegin.class, Cnd.where("parentid", "=", pid));
		return list;
	}

	public List<Template> getMyTemplateList(Long user_id) {
		List<Template> list = new ArrayList<Template>();
		try {
			list = mysqlDao.query(Template.class, Cnd.where("user_id", "=", user_id));
			for (Template tmpl : list) {

				List<TaskPage> pageList = mysqlDao.query(TaskPage.class, Cnd.where("tid", "=", tmpl.getId()));

				tmpl.setPageList(pageList);
			}
		} catch (Exception e) {
			logger.info("", e);

		}
		return list;
	}

	public List<Template> getMyConfigList(Long user_id) {

		try {
			List<Template> tlist = mysqlDao.query(Template.class, Cnd.where("user_id", "=", user_id));

			return tlist;
		} catch (Exception e) {
			logger.info("", e);

		}
		return null;
	}

	public Template getTemplate(String request_id) {

		try {
			Template tlist = mysqlDao.fetch(Template.class, Cnd.where("request_id", "=", request_id));

			return tlist;
		} catch (Exception e) {
			logger.info("", e);

		}
		return null;
	}

	public Boolean saveTemplateConfig(String id, String template_name, String pageRule, String taskRule,
			Integer minPage, Integer maxPage, String byType, Integer ispage, Date start_date, Date end_date) {
		Boolean flag = false;
		try {
			Date date = new Date();

			long tid = Long.parseLong(id);
			Template config = mysqlDao.fetch(Template.class, Cnd.where("id", "=", tid));

			if (config == null) {
				return false;
			} else {

				config.setPage_rule(pageRule);
				config.setTask_rule(taskRule);
				config.setTemplate_name(template_name);
				config.setStart_date(start_date);
				config.setEnd_date(end_date);
				config.setMin_page(minPage);
				config.setMax_page(maxPage);
				config.setJobStatus(-1);
				config.setIsconf(ispage);
				config.setUpdate_time(date);
				config.setByType(byType);
				mysqlDao.updateIgnoreNull(config);

				mysqlDao.clear(TaskPage.class, Cnd.where("tid", "=", tid));

				String prule = config.getPage_rule();

				int index = prule.indexOf("[[");
				int last = prule.indexOf("]]");

				if (index > 0 && last > 0 && ispage == 1) {
					String furl = prule.substring(0, index);

					String lurl = prule.substring(last + 2, prule.length());
					for (int i = minPage; i <= maxPage; i++) {
						String pageUlr = furl + i + lurl;
						TaskPage tp = new TaskPage();
						tp.setRequest_id(config.getRequest_id());
						tp.setTid(tid);
						tp.setUser_id(config.getUser_id());
						tp.setStatus(0);
						tp.setPage(i);
						tp.setPage_url(pageUlr);
						tp.setCreate_time(new Date());
						tp.setJob_id(config.getRequest_id() + "-" + i);
						mysqlDao.fastInsert(tp);
					}
				} else {

					TaskPage tp = new TaskPage();
					tp.setRequest_id(config.getRequest_id());
					tp.setTid(tid);
					tp.setUser_id(config.getUser_id());
					tp.setStatus(0);
					tp.setPage(1);
					tp.setPage_url(config.getUrl());
					tp.setCreate_time(new Date());
					tp.setJob_id(config.getRequest_id() + "-" + 1);
					mysqlDao.fastInsert(tp);

				}

			}
			flag = true;
		} catch (Exception e) {
			logger.info("", e);
		}
		return flag;
	}

	public Result doStartJob(String tid) {
		Result ret = new Result();
		try {

			Template config = mysqlDao.fetch(Template.class, Cnd.where("id", "=", tid));

			String msg = JSON.toJSONString(config);
			JSONObject send = HttpMQProducer.send(Const.topic, Const.pid, msg, "job");
			if (send.getString("sendStatus").equals("SEND_OK")) {
				mysqlDao.update(Template.class, Chain.make("job_status", 0), Cnd.where("id", "=", tid));
				mysqlDao.update(TaskPage.class, Chain.make("status", 0), Cnd.where("tid", "=", tid));
			} else {
				mysqlDao.update(Template.class, Chain.make("job_status", 3), Cnd.where("id", "=", tid));
			}

			ret.setResult(Const.SUC, "处理成功");
		} catch (Exception e) {
			logger.info("", e);
			ret.setResult(Const.FAIL, "处理异常");
		}
		return ret;
	}

	public static void main(String[] args) {
		String url = "http://www.yanjiao.com/forum-327-[[page]].html";
		int index = url.indexOf("[[");
		int last = url.indexOf("]]");
		String furl = url.substring(0, index);

		String lurl = url.substring(last + 2, url.length());

		logger.info(furl);
		logger.info(lurl);

	}

}
