package com.zhishu.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

@Table("t_export_table")
public class ExportTable {
    @Id(auto = true)
    private long id;
    @Column
    private long config_id;
    @Column
    private String name;
    @Column
    private String create_table_sql;
    @Column
    private String request_id;
    @Column
    private Date create_date;
    
    private DataConfig ds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getCreate_table_sql() {
        return create_table_sql;
    }

    public void setCreate_table_sql(String create_table_sql) {
        this.create_table_sql = create_table_sql;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public long getConfig_id() {
        return config_id;
    }

    public void setConfig_id(long config_id) {
        this.config_id = config_id;
    }

	public DataConfig getDs() {
		return ds;
	}

	public void setDs(DataConfig ds) {
		this.ds = ds;
	}
    
    
}
