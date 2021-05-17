package com.zhishu.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by guoyu on 2017/8/10.
 */
@Table("t_template_detail")
public class TemplateDetail implements java.io.Serializable, Cloneable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column
	private Long id;
	@Column
	private Long tid;
	@Column
	private String request_id;
	@Column
	private Long user_id;
	@Column
	private String column_name;
	@Column
	private String unique_id;
	@Column
	private String column_type;
	@Column
	private String list_path;
	@Column
	private String node_path;
	@Column
	private Integer node_length;
	@Column
	private String tag_name;
	@Column
	private Date create_time;
	@Column
	private String propert;
	@Column
	private Integer sibling_index;
	@Column
	private String node_value;
	public Integer getSibling_index() {
		return sibling_index;
	}

	public void setSibling_index(Integer sibling_index) {
		this.sibling_index = sibling_index;
	}

	private String th;

	List<TNode> elements = new ArrayList<TNode>();

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getColumn_type() {
		return column_type;
	}

	public void setColumn_type(String column_type) {
		this.column_type = column_type;
	}

	public String getList_path() {
		return list_path;
	}

	public void setList_path(String list_path) {
		this.list_path = list_path;
	}

	public String getNode_path() {
		return node_path;
	}

	public void setNode_path(String node_path) {
		this.node_path = node_path;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getUnique_id() {
		return unique_id;
	}

	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}

	public List<TNode> getElements() {
		return elements;
	}

	public void setElements(List<TNode> elements) {
		this.elements = elements;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPropert() {
		return propert;
	}

	public void setPropert(String propert) {
		this.propert = propert;
	}

	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	public Long getId() {
		return id;
	}

	public String getNode_value() {
		return node_value;
	}

	public void setNode_value(String node_value) {
		this.node_value = node_value;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getTh() {
		return th;
	}

	public void setTh(String th) {
		this.th = th;
	}

	public Integer getNode_length() {
		return node_length;
	}

	public void setNode_length(Integer node_length) {
		this.node_length = node_length;
	}

}
