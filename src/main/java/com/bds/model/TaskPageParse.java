package com.bds.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_task_page_parse")
public class TaskPageParse {

	@Id
	@Column
	private Long id;
	@Column
	private Long tid;
	@Column
	private Long tpid;
	@Column
	private Long user_id;
	@Column
	private String head;
	@Column
	private String title;
	@Column
	private String result;
	@Column
	private Date create_time;

	@Column
	private String job_id;
	@Column
	private String request_id;
	@Column
	private String page_url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTpid() {
		return tpid;
	}

	public void setTpid(Long tpid) {
		this.tpid = tpid;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getPage_url() {
		return page_url;
	}

	public void setPage_url(String page_url) {
		this.page_url = page_url;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJob_id() {
		return job_id;
	}

}
