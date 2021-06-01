package com.bds.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table("t_export_record")
public class ExportRecord {
	@Column
	private Long id;
	@Column
	private long user_id;
	@Column
	private long table_id;
	@Column
	private String request_id;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date create_time;
	@Column
	private Integer status;

	private ExportTable table;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getTable_id() {
		return table_id;
	}

	public void setTable_id(long table_id) {
		this.table_id = table_id;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ExportTable getTable() {
		return table;
	}

	public void setTable(ExportTable table) {
		this.table = table;
	}

}
