package com.guaishushu.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guaishushu.dao.OrderDao;
import com.guaishushu.dao.OrderDaoImpl;

/**
 * 对订单的一系列操作
 * 
 * @author 付旺辉
 * @date 2017年6月12日
 * @time 下午2:50:51
 */
public class OrderDeal extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		String type = request.getParameter("type");
		long order_id = Long.parseLong(request.getParameter("order_id"));
		OrderDao orderDao = new OrderDaoImpl();

		if ("delete".equals(type)) {
			// 如果是删除订单
			orderDao.deleteOrderById(order_id);
			// 跳转到订单详情页
			response.sendRedirect("/MyBookShop/ToShowOrder?type=all");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
