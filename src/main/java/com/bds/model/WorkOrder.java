package com.bds.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Table("t_work_order")
public class WorkOrder {
    @Id(auto = true)
    private long id;
    @Column
    private long user_id;
    @Column
    private long project_id;
  
    private String project_name;
    @Column
    private String work_no;
    @Column
    private String title;
    @Column
    private String type;
    @Column
    private String bind_no;
    @Column
    private String content;
    @Column
    private String attch_path;
    @Column
    private String attch_name;
    @Column
    private int status;
    @Column
	@JsonFormat(pattern = "yyyy-MM-dd")
    private Date create_time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getProject_id() {
        return project_id;
    }

    public void setProject_id(long project_id) {
        this.project_id = project_id;
    }

    public String getWork_no() {
        return work_no;
    }

    public void setWork_no(String work_no) {
        this.work_no = work_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBind_no() {
        return bind_no;
    }

    public void setBind_no(String bind_no) {
        this.bind_no = bind_no;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    

    public String getAttch_path() {
		return attch_path;
	}

	public void setAttch_path(String attch_path) {
		this.attch_path = attch_path;
	}

	public String getAttch_name() {
		return attch_name;
	}

	public void setAttch_name(String attch_name) {
		this.attch_name = attch_name;
	}

	public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
    
    
}
