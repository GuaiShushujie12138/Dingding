package com.guaishushu.bean;

import java.io.Serializable;
import java.util.Set;

/*
 * 订单
 */
public class Order implements Serializable, Comparable<Order> {
	private static final long serialVersionUID = 1L;
	private long id;
	// 订单中商品的总数量
	private long num;
	// 订单生成的时间
	private String start_date;
	// 所有产品的总价
	private double sum_price;
	// 支付方式
	private Payway payway;
	// 订单的状态:1，待付款，2完成，3评价
	private String status;
	// 订单所属的用户
	private User user;
	// 接受人详细信息
	private Receiver receiver;
	private Set<OrderLine> orderlines;

	public Order(long id, long num, String start_date, double sum_price,
			Payway payway, String status, Receiver receiver,
			Set<OrderLine> orderlines) {
		super();
		this.id = id;
		this.num = num;
		this.start_date = start_date;
		this.sum_price = sum_price;
		this.payway = payway;
		this.status = status;
		this.receiver = receiver;
		this.orderlines = orderlines;
	}

	public Order() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getSum_price() {
		return sum_price;
	}

	public void setSum_price(double sum_price) {
		this.sum_price = sum_price;
	}

	public Payway getPayway() {
		return payway;
	}

	public void setPayway(Payway payway) {
		this.payway = payway;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	public Set<OrderLine> getOrderlines() {
		return orderlines;
	}

	public void setOrderlines(Set<OrderLine> orderlines) {
		this.orderlines = orderlines;
	}

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	@Override
	/* 根据订单id判断是不是同一个订单 */
	public int compareTo(Order o) {
		return Long.valueOf(o.id).compareTo(id);
	}

}
