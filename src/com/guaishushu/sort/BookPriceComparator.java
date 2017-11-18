package com.guaishushu.sort;

import java.util.Comparator;

import com.guaishushu.bean.Product;

/**
 * 自定义图书价格比较器
 * 
 * @author 付旺辉
 * @date 2017年6月12日
 * @time 下午7:22:36
 */
public class BookPriceComparator implements Comparator<Product> {

	@Override
	public int compare(Product o1, Product o2) {
		int flag = Double.valueOf(o1.getPrice()).compareTo(o2.getPrice());
		if (flag == 0) {
			flag = o1.getName().compareTo(o2.getName());
		}
		return flag;
	}

}
