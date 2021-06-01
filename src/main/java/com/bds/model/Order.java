package com.bds.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_order")
public class Order {

    @Id(auto = true)
    @Column
    private Long id;
    @Column
    private Long user_id;
    @Column
    private String order_no;
    @Column
    private Double amount;
    @Column
    private String subject;
    @Column
    private String body;
    /** 购买套餐类型\r\n1.1个月58\r\n2.1个月158\r\n3.vip合作\r\n4.买次 */
    @Column
    private Integer type;
    /** 订单状态\r\n0:草稿\r\n1:待支付\r\n2.支付成功\r\n3.支付失败\r\n4.关闭 */
    @Column
    private Integer order_status;
    @Column
    private Date create_time;
    @Column
    private String trade_status;
    @Column
    private String trade_no;
    @Column
    private String trade_type;
    @Column
    private Date trade_time;

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

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getBody() {
	return body;
    }

    public void setBody(String body) {
	this.body = body;
    }

    public Integer getType() {
	return type;
    }

    public void setType(Integer type) {
	this.type = type;
    }

    public Integer getOrder_status() {
	return order_status;
    }

    public void setOrder_status(Integer order_status) {
	this.order_status = order_status;
    }

    public Date getCreate_time() {
	return create_time;
    }

    public void setCreate_time(Date create_time) {
	this.create_time = create_time;
    }

    public String getTrade_status() {
	return trade_status;
    }

    public void setTrade_status(String trade_status) {
	this.trade_status = trade_status;
    }

    public String getTrade_no() {
	return trade_no;
    }

    public void setTrade_no(String trade_no) {
	this.trade_no = trade_no;
    }

    public String getTrade_type() {
	return trade_type;
    }

    public void setTrade_type(String trade_type) {
	this.trade_type = trade_type;
    }

    public Date getTrade_time() {
	return trade_time;
    }

    public void setTrade_time(Date trade_time) {
	this.trade_time = trade_time;
    }

}
