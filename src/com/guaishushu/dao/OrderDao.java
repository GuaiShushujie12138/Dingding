package com.guaishushu.dao;

import java.util.Set;

import com.guaishushu.bean.Order;
import com.guaishushu.bean.OrderLine;

/**
 * 对订单表和订单项表的操作
 * 
 * @author 付旺辉
 * @date 2017年6月8日
 * @time 上午11:21:51
 */
public interface OrderDao {
	/* 把数据插入订单表 和订单项表,以及收件人表 , 返回订单id */
	long insertDataToOrderAndLine(Set<OrderLine> orderLines, long receiver_id,
			long user_id, long payway_id);

	/* 根据订单id查找所有的订单项 */
	Set<OrderLine> findAllOrderLinesByOrderId(long order_id);

	/* 更新订单表中订单的状态 ,以及支付状态 */
	void updateOrderStatus(long order_id, String status, long payway_id);

	/* 更新订单表中订单的状态 */
	void updateOrderStatus(long order_id, String status);

	/* 根据订单id查找订单 */
	Order findOrderByOrderId(long order_id);

	/* 根据用户id查找用户所有的订单 */
	Set<Order> findAllOrdersByUserId(long user_id);

	/* 根据订单号删除订单 ,必须先行删除跟订单相关的订单项表数据 */
	void deleteOrderById(long order_id);

	/* 根据订单项id删除订单项数据 */
	void deleteOrderLineById(long orderLine_id);
}
