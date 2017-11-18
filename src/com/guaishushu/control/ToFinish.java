package com.guaishushu.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToFinish extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		// 获取订单id
		long order_id = Long.parseLong(request.getParameter("order_id"));
		// 存放进request中
		request.setAttribute("order_id", order_id);

		// 跳转到订单完成的页面
		request.getRequestDispatcher("WEB-INF/bookshop/finish.jsp").forward(
				request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
