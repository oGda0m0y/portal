package com.bds.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_transaction")
public class Transaction {

	@Id(auto = true)
	@Column
	private Long id;
	@Column
	private Long user_id;
	@Column
	private Long order_id;
	@Column
	private String order_no;
	@Column
	private Double amount;
	@Column
	private Integer pay_type;
	@Column
	private String remark;
	@Column
	private String trade_no;
	@Column
	private Integer transaction_status;
	@Column
	private Date create_time;

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

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getPay_type() {
		return pay_type;
	}

	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getTransaction_status() {
		return transaction_status;
	}

	public void setTransaction_status(Integer transaction_status) {
		this.transaction_status = transaction_status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getTrade_no() {
	    return trade_no;
	}

	public void setTrade_no(String trade_no) {
	    this.trade_no = trade_no;
	}
	

}
