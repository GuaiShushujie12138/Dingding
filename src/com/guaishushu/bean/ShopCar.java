package com.guaishushu.bean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShopCar implements IShopCar {
	// 把bookId和book的version进行拼接作为key值
	private Map<String, OrderLine> orderlines;

	public ShopCar() {
		orderlines = new HashMap<String, OrderLine>();
	}

	public Map<String, OrderLine> getMap() {
		return orderlines;
	}

	public void addProduct(Product product) throws Exception {
		System.out.println("进入ShopCart的add()方法！");
		long number = 1;
		long id = product.getId();
		int version = product.getVersion();
		if (orderlines.containsKey(id + "" + version)) {
			OrderLine orderline = (OrderLine) orderlines.get(id + "" + version);
			number = orderline.getAmount() + 1L;
			orderline.setAmount(number);
			orderline.setA_price(product.getPrice());
		} else {
			OrderLine orderline = new OrderLine();
			orderline.setAmount(new Long(number));
			orderline.setProduct(product);
			orderline.setProduct_version(version);
			orderline.setA_price(product.getPrice());
			orderlines.put(id + "" + version, orderline);
		}

	}

	public void removeProduct(long productid, int version) throws Exception {
		System.out.println("进入ShopCart的removeProduct()方法！");
		// map集合移除订单项
		orderlines.remove(productid + "" + version);
	}

	public void clear() throws Exception {
		System.out.println("进入ShopCart的removeAllProducts()方法！");
		// 把map集合全部清空
		orderlines.clear();
	}

	public void updateProduct(long productid, Integer number, int version)
			throws Exception {
		System.out.println("进入ShopCart的updateProduct()方法！");
		OrderLine orderline = orderlines.get(productid + "" + version);
		System.out.println("ShopCar updateProduct number : " + number);
		if (number == 0) {
			// 直接删除
			removeProduct(productid, version);
		} else {
			orderline.setAmount(number);
		}
	}

	public BigDecimal getTotalPrice() throws Exception {
		System.out.println("进入ShopCart的getTotalPrice()方法！");
		BigDecimal totalPrice = new BigDecimal(0);
		Iterator<OrderLine> iter = getOrderlines();
		// 判断下一个值是否有值iter.hasNext()
		while (iter.hasNext()) {
			// 获取值
			OrderLine o = (OrderLine) iter.next();
			Product p = o.getProduct();
			BigDecimal sum = new BigDecimal(o.getA_price() * o.getAmount());
			totalPrice = totalPrice.add(sum);
		}
		return totalPrice;
	}

	// 获取迭代器，对map集合的遍历方式
	public Iterator<OrderLine> getOrderlines() throws Exception {
		System.out.println("进入ShopCart的getOrderlines()方法！");
		// orderlines.values()得到所有的value值--》Set集合
		return orderlines.values().iterator();
	}

	// 向选中的订单向里面添加图书

}
