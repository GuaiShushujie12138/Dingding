package com.guaishushu.dao;

import java.util.Set;

import com.guaishushu.bean.Evaluate;

/**
 * 对评价表的操作
 * 
 * @author 付旺辉
 * @date 2017年6月15日
 * @time 下午3:05:03
 */
public interface EvaluateDao {
	/* 添加评价 */
	void addEvaluate(Evaluate evaluate, long user_id, long book_id);

	/* 通过评价id删除评价 */
	void deleteEvaluateById(long evaluate_id);

	/* 通过用户id查询用户所有的评价 */
	Set<Evaluate> findEvaluatesByUserId(long user_id);

	/* 通过图书id查询所有的有关该图书的评价 */
	Set<Evaluate> findEvaluatesByBookId(long book_id);

	/* 通过订单号查询评价 */
	Set<Evaluate> findEvaluatesByOrderId(long order_id, long user_id);
}
