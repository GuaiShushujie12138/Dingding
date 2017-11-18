package com.guaishushu.bean;

/*
 * 杰普简报
 */
public class Report implements Comparable<Report> {
	private long id;
	// 标题
	private String title;
	private Product product;// 对应的图书
	// 简报生成时间
	private String start_date;

	public Report(long id, String title, Product product) {
		super();
		this.id = id;
		this.title = title;
		this.product = product;
	}

	public Report() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int compareTo(Report o) {
		return Long.valueOf(id).compareTo(o.id);
	}

}
