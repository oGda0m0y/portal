package com.zhishu.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhishu.portal.util.CustomTimeSerializer;

/**
 * Created by guoyu on 2017/8/11.
 */
@Table("t_template")
public class Template {
	@Id
	@Column
	private long id;
	@Column
	private String request_id;
	@Column
	private long user_id;
	@Column
	private String template_name;
	@Column
	private String url;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date create_time;
	@Column
	private String page_rule;
	@Column
	private String props;
	@Column
	private String task_rule;
	@Column("by_type")
	private String byType;
	@Column
	private Integer min_page;
	@Column
	private Integer max_page;
	@Column
	private Date update_time;
	@Column
	private Date start_date;
	@Column
	private Date end_date;
	@Column("job_status")
	private Integer jobStatus;
	@Column
	private Integer isconf;

	private List<TaskPage> pageList = new ArrayList<TaskPage>();

	private List<TemplateDetail> detailList = new ArrayList<TemplateDetail>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getTemplate_name() {
		return template_name;
	}

	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@JsonSerialize(using = CustomTimeSerializer.class)
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getPage_rule() {
		return page_rule;
	}

	public void setPage_rule(String page_rule) {
		this.page_rule = page_rule;
	}

	public String getTask_rule() {
		return task_rule;
	}

	public void setTask_rule(String task_rule) {
		this.task_rule = task_rule;
	}

	public String getByType() {
		return byType;
	}

	public void setByType(String byType) {
		this.byType = byType;
	}

	public Integer getMin_page() {
		return min_page;
	}

	public void setMin_page(Integer min_page) {
		this.min_page = min_page;
	}

	public Integer getMax_page() {
		return max_page;
	}

	public void setMax_page(Integer max_page) {
		this.max_page = max_page;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Integer getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Integer jobStatus) {
		this.jobStatus = jobStatus;
	}

	public List<TaskPage> getPageList() {
		return pageList;
	}

	public void setPageList(List<TaskPage> pageList) {
		this.pageList = pageList;
	}

	public List<TemplateDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<TemplateDetail> detailList) {
		this.detailList = detailList;
	}

	public String getProps() {
		return props;
	}

	public void setProps(String props) {
		this.props = props;
	}

	public Integer getIsconf() {
		return isconf;
	}

	public void setIsconf(Integer isconf) {
		this.isconf = isconf;
	}

}
