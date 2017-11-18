package com.guaishushu.bean;

/*
 * 书籍种类
 */

import java.io.Serializable;
import java.util.Map;

public class Category implements Serializable, Comparable<Category> {
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private Map<Long, CategoryDetail> cate_detail;

	public Category() {
		super();
	}

	public Category(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

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

	public Map<Long, CategoryDetail> getCate_detail() {
		return cate_detail;
	}

	public void setCate_detail(Map<Long, CategoryDetail> cate_detail) {
		this.cate_detail = cate_detail;
	}

	@Override
	public int compareTo(Category o) {
		return name.compareTo(o.name);
	}

}
