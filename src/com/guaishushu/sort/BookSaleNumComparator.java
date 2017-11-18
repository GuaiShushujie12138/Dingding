package com.guaishushu.sort;

import java.util.Comparator;

import com.guaishushu.bean.Product;

/**
 * 自定义图书销量比较器
 * 
 * @author 付旺辉
 * @date 2017年6月12日
 * @time 下午7:20:33
 */
public class BookSaleNumComparator implements Comparator<Product> {

	@Override
	public int compare(Product o1, Product o2) {
		int flag = Long.valueOf(o2.getSale_num()).compareTo(o1.getSale_num());
		if (flag == 0) {
			flag = o1.getName().compareTo(o2.getName());
		}
		return flag;
	}

}
