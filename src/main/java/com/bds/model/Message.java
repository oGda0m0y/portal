package com.bds.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table("t_message")
public class Message {

	@Id(auto=true)
	@Column
	private Long id;
	@Column
	private Long user_id;
	@Column
	private Long biz_id;
	@Column
	private String biz_table;
	@Column
	private Long template_id;
	@Column
	private String content;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date create_time;
	private String msg;
	private String type;
	@Column
	private Integer isread;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getBiz_id() {
		return biz_id;
	}

	public void setBiz_id(Long biz_id) {
		this.biz_id = biz_id;
	}

	public String getBiz_table() {
		return biz_table;
	}

	public void setBiz_table(String biz_table) {
		this.biz_table = biz_table;
	}

	public Long getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(Long template_id) {
		this.template_id = template_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIsread() {
		return isread;
	}

	public void setIsread(Integer isread) {
		this.isread = isread;
	}

	

}
