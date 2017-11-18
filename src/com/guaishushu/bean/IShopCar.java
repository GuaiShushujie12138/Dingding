package com.guaishushu.bean;

import java.math.BigDecimal;
import java.util.Iterator;

public interface IShopCar {

	void addProduct(Product product) throws Exception;

	void removeProduct(long productid, int version) throws Exception;

	void clear() throws Exception;

	void updateProduct(long productid, Integer number, int version)
			throws Exception;

	BigDecimal getTotalPrice() throws Exception;

	Iterator<OrderLine> getOrderlines() throws Exception;

}
