package com.guaishushu.sort;

import java.util.Comparator;

import com.guaishushu.bean.Product;

/**
 * 自定义图书出版日期比较器
 * 
 * @author 付旺辉
 * @date 2017年6月12日
 * @time 下午7:23:23
 */
public class BookDateComparator implements Comparator<Product> {

	@Override
	public int compare(Product o1, Product o2) {
		int flag = o1.getPublish_date().compareTo(o2.getPublish_date());
		if (flag == 0) {
			flag = o1.getName().compareTo(o2.getName());
		}
		return flag;
	}
}
