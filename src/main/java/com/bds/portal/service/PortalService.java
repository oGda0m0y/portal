package com.bds.portal.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.codehaus.plexus.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.SendResult;
import com.bds.model.Result;
import com.bds.model.Task;
import com.bds.portal.queue.TcpApiProducer;
import com.bds.portal.queue.TcpTaskProducer;
import com.bds.portal.util.Const;
import com.bds.portal.util.HTMLUploadOSSUtil;

@Component
public class PortalService {
	private static Logger logger = Logger.getLogger(PortalService.class);

	@Resource
	private NutDao mysqlDao;

	public Result addTask(String requestId, String url, String uuid, String userId) {
		Result ret = new Result();
		try {
			// IServiceApi apiService = (IServiceApi)
			// factory.create(IServiceApi.class, Const.TASK_API_URL);

			final Task task = new Task();
			task.setCreate_time(new Date());
			task.setUuid(uuid);
			if (StringUtils.isNotEmpty(userId)) {
				task.setUserId(Long.parseLong(userId));
			}
			task.setUrl(url);
			task.setRequestId(requestId);
			task.setStatus(0);
			mysqlDao.fastInsert(task);
			JSONObject msg = new JSONObject();
			msg.put("url", url);
			msg.put("id", task.getId());
			msg.put("requestId", requestId);
			msg.put("userId", task.getUserId());

			SendResult send = TcpApiProducer.getInstance().send(msg.toJSONString());
			// ret = apiService.doJob(url, task.getId(), requestId,
			// task.getUserId());
			if (send != null && send.getMessageId() != null) {
				mysqlDao.update(Task.class, Chain.make("status", 1), Cnd.where("id", "=", task.getId()));
				ret.setResult(Const.SUC, "发送成功");
			} else {
				mysqlDao.update(Task.class, Chain.make("status", 3), Cnd.where("d", "=", task.getId()));
				ret.setResult(Const.FAIL_400, "发送失败");
			}
		} catch (Exception e) {
			ret.setResult(Const.FAIL_400, "服务器异常");
			logger.error("", e);
		}
		return ret;
	}

	public Result getHtml(String requestId) {
		Result ret = new Result();
		try {
			String html = HTMLUploadOSSUtil.downloadHtml(requestId);
			if (StringUtils.isNotEmpty(html)) {
				Document doc = Jsoup.parse(html);
				// 返回前端时，去除a标签中的href
				Elements elements = doc.getAllElements();
				for (int i = 0; i < elements.size(); i++) {
					Element e = elements.get(i);
					if (e.tagName().equals("a")) {
						e.attributes().remove("href");
					}
				}

				html = doc.html();
			}

			ret.setCode(Const.SUC);
			ret.setData(html);
		} catch (Exception e) {
			ret.setResult(Const.FAIL_500, "获取html异常");
		}
		return ret;
	}

	public Task getTask(String requestId) {

		try {
			Task task = mysqlDao.fetch(Task.class, Cnd.where("request_id", "=", requestId));
			return task;
		} catch (Exception e) {

			logger.error("", e);
		}
		return null;
	}

	public String getTaskUrl(String requestId) {
		String ret = "";
		try {
			String url = mysqlDao.fetch(Task.class, Cnd.where("request_id", "=", requestId)).getUrl();
			return url;
		} catch (Exception e) {
			logger.error("", e);
		}
		return ret;
	}
}
