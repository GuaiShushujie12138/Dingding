package com.guaishushu.bean;

/*
 * 检索的时候价格的范围
 */
public class PriceRank implements Comparable<PriceRank> {
	private long id;
	// 最小价钱
	private long min_price;
	// 最高价钱
	private long max_price;

	public PriceRank(long id, long min_price, long max_price) {
		super();
		this.id = id;
		this.min_price = min_price;
		this.max_price = max_price;
	}

	public PriceRank() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMin_price() {
		return min_price;
	}

	public void setMin_price(long min_price) {
		this.min_price = min_price;
	}

	public long getMax_price() {
		return max_price;
	}

	public void setMax_price(long max_price) {
		this.max_price = max_price;
	}

	@Override
	public int compareTo(PriceRank o) {
		return Long.valueOf(id).compareTo(o.id);
	}
}
