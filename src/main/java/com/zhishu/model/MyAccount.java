package com.zhishu.model;

import java.math.BigDecimal;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.View;

@View("v_my_account")
public class MyAccount {
	@Id
	@Column
	private Long id;
	@Column
	private Long user_id;
	@Column("amount")
	private BigDecimal amount;
	@Column("sumsale")
	private BigDecimal  sumsale;
	@Column("lastday")
	private BigDecimal  lastday;
	private Double totalAmount;
	private Double  totalSale;
	private Double  lastSale;
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
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getSumsale() {
		return sumsale;
	}
	public void setSumsale(BigDecimal sumsale) {
		this.sumsale = sumsale;
	}
	public BigDecimal getLastday() {
		return lastday;
	}
	public void setLastday(BigDecimal lastday) {
		this.lastday = lastday;
	}
	public Double getTotalSale() {
		return totalSale;
	}
	public void setTotalSale(Double totalSale) {
		this.totalSale = totalSale;
	}
	public Double getLastSale() {
		return lastSale;
	}
	public void setLastSale(Double lastSale) {
		this.lastSale = lastSale;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
