package com.guaishushu.bean;

import java.io.Serializable;
import java.util.Set;

/*
 * 出版社
 */
public class Publish implements Serializable, Comparable<Publish> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	// 出版社的名字
	private String name;
	private Set<Product> products;

	public Publish(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Publish() {
		super();
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

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public int compareTo(Publish o) {
		return name.compareTo(o.name);
	}

}
