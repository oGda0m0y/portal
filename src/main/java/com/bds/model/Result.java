package com.bds.model;

public class Result implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1552682779157307387L;
	// 返回码
	private Integer code;
	// 返回码
	private boolean ret;
	// 返回消息
	private String msg;
	// 返回数据对象
	private Object data;
	// 返回数据对象
	private Object info;

	public Result() {
	}

	public Result(Integer code, String message) {
		this.code = code;
		this.msg = message;
	}

	public Result(boolean ret, String message) {
		this.ret = ret;
		this.msg = message;
	}

	public Result(Integer code, Object data) {
		this.code = code;
		this.data = data;
	}

	public void setResult(Integer code, String message) {
		this.code = code;
		this.msg = message;
	}

	public void setResult(boolean ret, String message) {
		this.ret = ret;
		this.msg = message;
	}

	public void setResult(boolean ret, Object data) {
		this.ret = ret;
		this.data = data;
	}

	public void setResult(Integer code, Object data) {
		this.code = code;
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public boolean isRet() {
		return ret;
	}

	public void setRet(boolean ret) {
		this.ret = ret;
	}

}
