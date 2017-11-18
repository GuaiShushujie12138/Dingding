package com.guaishushu.control;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guaishushu.bean.Order;
import com.guaishushu.bean.User;
import com.guaishushu.dao.OrderDao;
import com.guaishushu.dao.OrderDaoImpl;

public class ToShowOrder extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		OrderDao orderDao = new OrderDaoImpl();
		// 订单
		Set<Order> orders = new TreeSet<Order>();
		String type1 = "";

		// 过去当前用户是要查看单个订单还是全部订单
		String type = request.getParameter("type");
		if ("single".equals(type)) {
			// 如果是查看单个订单
			// 获取订单号
			long order_id = Long.parseLong(request.getParameter("order_id"));

			// 根据订单号查询订单
			Order order = orderDao.findOrderByOrderId(order_id);
			orders.add(order);
			type1 = "single";
		} else if ("all".equals(type)) {
			// 如果是查看所有订单
			// 获取用户id
			long user_id = ((User) (request.getSession()
					.getAttribute("login_user"))).getId();
			orders = orderDao.findAllOrdersByUserId(user_id);

			// /* 测试 */
			// Iterator<Order> it = orders.iterator();
			// while (it.hasNext()) {
			// Order order = it.next();
			// Set<OrderLine> orderLines = order.getOrderlines();
			// Iterator<OrderLine> it2 = orderLines.iterator();
			// while (it2.hasNext()) {
			// OrderLine orderLine = it2.next();
			// System.out.println(orderLine);
			// }
			// }

			type1 = "all";
		}

		// 存入request中
		request.setAttribute("orders", orders);
		request.setAttribute("type", type1);

		// 跳转到展示订单的页面
		request.getRequestDispatcher("WEB-INF/bookshop/showorder.jsp").forward(
				request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
