package com.zhishu.model;

import com.alibaba.fastjson.JSONArray;

public class ExportModel {

	private JSONArray list = new JSONArray();

	private JSONArray head = new JSONArray();

	public JSONArray getList() {
		return list;
	}

	public void setList(JSONArray list) {
		this.list = list;
	}

	public JSONArray getHead() {
		return head;
	}

	public void setHead(JSONArray head) {
		this.head = head;
	}

}
