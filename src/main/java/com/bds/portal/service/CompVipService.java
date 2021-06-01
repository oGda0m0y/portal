
package com.bds.portal.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.springframework.stereotype.Component;

import com.bds.model.Project;
import com.bds.model.WorkOrder;
import com.bds.portal.common.result.Page;
import com.bds.portal.util.Const;

@Component
public class CompVipService {

	private static Logger logger = Logger.getLogger(CompVipService.class);

	@Resource
	private NutDao mysqlDao;

	public Page getCompProject(Long user_id, Integer pageNum, Integer pageSize) {
		Pager pager = mysqlDao.createPager(pageNum, pageSize);
		Criteria cri = Cnd.cri();
		cri.where().andEquals("user_id", user_id);
		List<Project> list = mysqlDao.query(Project.class, cri, pager);

		int count = mysqlDao.count(Project.class, cri);
		Page p = new Page(count, pager.getPageNumber(), pager.getPageSize());
		p.setCode(Const.SUC);
		p.setData(list);
		return p;

	}

	public Page getCompWorkOrder(Long user_id, Integer pageNum, Integer pageSize) {
		Pager pager = mysqlDao.createPager(pageNum, pageSize);
		Criteria cri = Cnd.cri();
		cri.where().andEquals("user_id", user_id);
		List<WorkOrder> list = mysqlDao.query(WorkOrder.class, cri, pager);
		for (WorkOrder e : list) {
			Project pp = mysqlDao.fetch(Project.class, e.getProject_id());
			e.setProject_name(pp.getProject_name());
		}

		int count = mysqlDao.count(WorkOrder.class, cri);
		Page p = new Page(count, pager.getPageNumber(), pager.getPageSize());
		p.setCode(Const.SUC);
		p.setData(list);
		return p;

	}

	public boolean saveCompProject(Long user_id, String projectName, String website) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		boolean ret = true;
		try {

			DecimalFormat df = new DecimalFormat("0000");
			String index = df.format(user_id);
			String last = RandomStringUtils.randomNumeric(6);
			String projectCode = "P" + index + formatter.format(new Date()) + last;
			Project p = new Project();
			p.setCreate_time(new Date());
			p.setProject_code(projectCode);
			p.setProject_name(projectName);
			p.setUser_id(user_id);
			p.setWebsite(website);
			mysqlDao.fastInsert(p);
		} catch (Exception e) {
			logger.error("", e);
			ret = false;
		}
		return ret;

	}

	public List<Project> getProject(Long id) {
		List<Project> list = mysqlDao.query(Project.class, Cnd.where("user_id", "=", id));
		return list;
	}

	public boolean saveWorkOrder(Long user_id, WorkOrder workOrder) {
		Boolean flag = false;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			DecimalFormat df = new DecimalFormat("0000");
			String index = df.format(user_id);

			String last = RandomStringUtils.randomNumeric(6);
			String projectCode = "ZS" + index + formatter.format(new Date()) + last;

			workOrder.setUser_id(user_id);

			workOrder.setWork_no(projectCode);

			workOrder.setStatus(0);
			workOrder.setCreate_time(new Date());

			mysqlDao.insert(workOrder);
			flag = true;
		} catch (Exception e) {
			logger.info("", e);
		}
		return flag;
	}
}
