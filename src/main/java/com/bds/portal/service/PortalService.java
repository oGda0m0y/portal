package com.bds.portal.service;

import java.net.MalformedURLException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.codehaus.plexus.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.springframework.stereotype.Component;

import com.bds.api.IServiceApi;
import com.bds.model.Result;
import com.bds.model.Task;
import com.bds.portal.util.Const;
import com.bds.portal.util.HTMLUploadOSSUtil;
import com.caucho.hessian.client.HessianProxyFactory;

@Component
public class PortalService {
	private static Logger logger = Logger.getLogger(PortalService.class);
	private static HessianProxyFactory factory = new HessianProxyFactory();
	@Resource
	private NutDao mysqlDao;

	public Result addTask(String requestId, String url, String uuid, String userId) {
		Result ret = new Result();
		try {
			IServiceApi apiService = (IServiceApi) factory.create(IServiceApi.class, Const.TASK_API_URL);
			ret = apiService.addTask(requestId, url, uuid, userId);
		} catch (MalformedURLException e) {
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
