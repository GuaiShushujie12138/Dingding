package com.guaishushu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.guaishushu.bean.Order;
import com.guaishushu.bean.OrderLine;
import com.guaishushu.bean.Payway;
import com.guaishushu.bean.Product;
import com.guaishushu.bean.Receiver;
import com.guaishushu.bean.User;
import com.guaishushu.db.ConnectionFactory;
import com.guaishushu.db.SQLHelper;

public class OrderDaoImpl implements OrderDao {
	private SQLHelper helper;

	@Override
	/*
	 * 将用户生成的订单数据插入数据库中,要对订单表、订单项表同时操作所以涉及到数据库的事务,不借助SQLHelper帮助类单独做
	 */
	public long insertDataToOrderAndLine(Set<OrderLine> orderLines,
			long receiver_id, long user_id, long payway_id) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			// 获取连接
			conn = ConnectionFactory.getConnection();

			// 设置不自动提交,为了事务的安全性
			conn.setAutoCommit(false);
			// 设置事物的串行化,让其更加安全
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

			/**
			 * 1.向订单表中插入数据
			 */
			String sql = "insert into d_order values (d_order_seq.nextVal, ?, sysdate, ?, ?, ?, ?, ?)";
			pst = conn.prepareStatement(sql);
			int num = 0;// 订单中商品的总数
			double sum_price = 0.00;// 订单总价

			Iterator<OrderLine> it = orderLines.iterator();
			while (it.hasNext()) {
				OrderLine orderLine = it.next();
				sum_price += orderLine.getA_price() * orderLine.getAmount();
				num += orderLine.getAmount();
			}

			sum_price += 6;// 加上运费

			String status = "1";// 支付状态,待付款

			// pst中的参数
			Object[] params = new Object[] { num, sum_price, payway_id, status,
					user_id, receiver_id };
			// 赋值
			for (int i = 1; i <= params.length; i++) {
				pst.setObject(i, params[i - 1]);
			}
			// 执行插入操作
			pst.executeUpdate();
			System.out.println("向订单表中插入数据成功!");

			/**
			 * 2.向订单项表中插入数据
			 */

			// 查询刚刚插入订单表的订单id
			sql = "select d_order_seq.currVal from dual";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			long order_id = -1;
			while (rs.next()) {
				order_id = rs.getLong(1);
			}

			if (order_id != -1) {
				// 说明执行成功了
			} else {
				throw new RuntimeException("查询当前订单表的id失败!");
			}

			Iterator<OrderLine> it2 = orderLines.iterator();
			while (it2.hasNext()) {
				OrderLine orderLine = it2.next();

				/* 测试 */
				// System.out.println(orderLine);

				Product book = orderLine.getProduct();

				sql = "insert into d_orderline values (d_orderline_seq.nextVal, ?, ?, ?, ?, ?, ?)";
				pst = conn.prepareStatement(sql);
				params = new Object[] { orderLine.getAmount(),
						orderLine.getA_price() * orderLine.getAmount(),
						orderLine.getProduct_version(), orderLine.getA_price(),
						order_id, book.getId() };
				// 赋值
				for (int i = 1; i <= params.length; i++) {
					pst.setObject(i, params[i - 1]);
				}
				// 添加进batch中
				pst.executeUpdate();
			}

			// // 批量插入
			// pst.executeBatch();
			System.out.println("向订单项表中插入数据成功!");

			// 提交
			conn.commit();

			return order_id;

		} catch (Exception e) {
			try {
				// 出错就回滚,要不全部成功要不全部失败,保证安全
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	@Override
	/* 根据订单id查找所有的订单项 */
	public Set<OrderLine> findAllOrderLinesByOrderId(long order_id) {
		helper = new SQLHelper();
		Set<OrderLine> orderLines = new TreeSet<OrderLine>();

		String sql = "select * from d_orderline where order_id = ?";
		Long[] params = { order_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;
		OrderLine orderLine = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				long id = Long.parseLong(map.get("ID").toString());// 订单项编号
				int amount = Integer.parseInt(map.get("AMOUNT").toString());// 订单项中图书数量
				double single_price = Double.parseDouble(map
						.get("SINGLE_PRICE").toString());// 订单项中的总价
				int product_version = Integer.parseInt(map.get(
						"PRODUCT_VERSION").toString());// 图书的版本
				double a_price = Double.parseDouble(map.get("A_PRICE")
						.toString());// 订单项中图书的价格
				long product_id = Long.parseLong(map.get("PRODUCT_ID")
						.toString());// 图书id

				// 根据图书id查找图书
				BookDao bookDao = new BookDaoImpl();
				Product book = bookDao.findBookById(product_id);

				orderLine = new OrderLine(id, amount, single_price, a_price,
						book, product_version);

				orderLines.add(orderLine);
			}
		}

		return orderLines;
	}

	@Override
	/* 更新订单状态 */
	public void updateOrderStatus(long order_id, String status, long payway_id) {
		helper = new SQLHelper();

		String sql = "update d_order set status = ?, payway_id = ? where id = ?";
		Object[] params = new Object[] { status, payway_id, order_id };
		helper.update(sql, params);
	}

	@Override
	/* 根据订单id查找订单 */
	public Order findOrderByOrderId(long order_id) {
		helper = new SQLHelper();
		Order order = null;

		String sql = "select * from d_order where id = ?";
		Long[] params = { order_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				long num = Long.parseLong(map.get("NUM").toString());
				String start_date = (String) map.get("START_DATE");
				double sum_price = Double.parseDouble(map.get("SUM_PRICE")
						.toString());

				long payway_id = Long
						.parseLong(map.get("PAYWAY_ID").toString());
				// 根据payway_id查找Payway
				BookDao bookDao = new BookDaoImpl();
				Payway payway = bookDao.findPaywayById(payway_id);

				String status = (String) map.get("STATUS");

				long user_id = Long.parseLong(map.get("USER_ID").toString());
				// 根据user_id查找User
				UserDao userDao = new UserDaoImpl();
				User user = userDao.findUserById(user_id);

				long receiver_id = Long.parseLong(map.get("RECEIVER_ID")
						.toString());
				// 根据receiver_id查找Receiver
				ReceiverDao receiverDao = new ReceiverDaoImpl();
				Receiver receiver = receiverDao.findReceiverById(receiver_id);

				// 根据order_id 查找所有的订单项
				Set<OrderLine> orderLines = findAllOrderLinesByOrderId(order_id);

				order = new Order(order_id, num, start_date, sum_price, payway,
						status, receiver, orderLines);
			}
		}

		return order;
	}

	@Override
	/* 根据user_id查询用户下所有的订单 */
	public Set<Order> findAllOrdersByUserId(long user_id) {
		helper = new SQLHelper();
		Set<Order> orders = new TreeSet<Order>();

		// 根据user_id查询所有的订单id
		String sql = "select id from d_order where user_id = ?";
		Long[] params = { user_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;
		Order order = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				long order_id = Long.parseLong(map.get("ID").toString());
				order = findOrderByOrderId(order_id);
				orders.add(order);
			}
		}

		return orders;
	}

	@Override
	/* 根据订单号删除订单数据 */
	public void deleteOrderById(long order_id) {
		helper = new SQLHelper();

		// 先删除有关联的订单项数据
		deleteOrderLineById(order_id);

		// 最后再删除订单数据
		String sql = "delete from d_order where id = ?";
		Long[] params = { order_id };
		helper.delete(sql, params);

	}

	@Override
	/* 根据订单号删除订单项数据 */
	public void deleteOrderLineById(long order_id) {
		helper = new SQLHelper();

		String sql = "delete from d_orderline where order_id = ?";
		Long[] params = { order_id };
		helper.delete(sql, params);
	}

	@Override
	/* 更新订单状态,把订单状态改为已评价 */
	public void updateOrderStatus(long order_id, String status) {
		helper = new SQLHelper();

		String sql = "update d_order set status = ? where id = ?";
		Object[] params = new Object[] { status, order_id };
		helper.update(sql, params);
	}
}
