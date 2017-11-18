package com.guaishushu.bean;

import java.io.Serializable;

//订单项
public class OrderLine implements Serializable, Comparable<OrderLine> {
	private static final long serialVersionUID = 1L;
	private long id;
	// 购买产品的数量
	private long amount;
	// 一类产品的总价
	private double single_price;
	// 单个产品的价钱
	private double a_price;
	// 产品
	private Product product;
	// 属于的订单
	private Order order;
	// 产品的版本
	private int product_version;

	public OrderLine(long id, long amount, double single_price,
			Product product, int product_version) {
		super();
		this.id = id;
		this.amount = amount;
		this.single_price = single_price;
		this.product = product;
		this.product_version = product_version;
	}

	public OrderLine(long id, long amount, double single_price, double a_price,
			Product product, int product_version) {
		super();
		this.id = id;
		this.amount = amount;
		this.single_price = single_price;
		this.a_price = a_price;
		this.product = product;
		this.product_version = product_version;
	}

	public double getA_price() {
		return a_price;
	}

	public void setA_price(double a_price) {
		this.a_price = a_price;
	}

	public OrderLine() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public double getSingle_price() {
		return single_price;
	}

	public void setSingle_price(double single_price) {
		this.single_price = single_price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getProduct_version() {
		return product_version;
	}

	public void setProduct_version(int product_version) {
		this.product_version = product_version;
	}

	@Override
	public int compareTo(OrderLine o) {
		int flag = product.getName().compareTo(o.product.getName());
		if (flag == 0) {
			flag = Integer.valueOf(product_version)
					.compareTo(o.product_version);
		}
		return flag;
	}

	@Override
	public String toString() {
		return "OrderLine [id=" + id + ", amount=" + amount + ", single_price="
				+ single_price + ", a_price=" + a_price + ", product="
				+ product + ", order=" + order + ", product_version="
				+ product_version + "]";
	}

}
