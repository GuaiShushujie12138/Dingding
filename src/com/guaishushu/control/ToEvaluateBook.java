package com.guaishushu.control;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guaishushu.bean.OrderLine;
import com.guaishushu.dao.OrderDao;
import com.guaishushu.dao.OrderDaoImpl;

/**
 * 评价商品
 * 
 * @author 付旺辉
 * @date 2017年6月15日
 * @time 上午10:13:11
 */
public class ToEvaluateBook extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		OrderDao orderDao = new OrderDaoImpl();

		// 获取要评价的订单号
		long order_id = Long.parseLong(request.getParameter("order_id"));
		// 根据订单号查询所有的订单项
		Set<OrderLine> orderLines = orderDao
				.findAllOrderLinesByOrderId(order_id);

		// 存放进request中
		request.setAttribute("orderLines", orderLines);

		// 跳转
		request.getRequestDispatcher("WEB-INF/bookshop/evaluatebook.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
