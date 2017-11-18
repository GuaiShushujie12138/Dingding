package com.guaishushu.control;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guaishushu.bean.Payway;
import com.guaishushu.dao.BookDao;
import com.guaishushu.dao.BookDaoImpl;

public class ToPayWay extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		// 准备数据
		String totalPrice = request.getParameter("totalPrice");
		String bookIds = request.getParameter("bookIds");
		long order_id = Long.parseLong(request.getParameter("order_id"));

		// 获取所有的支付方式
		BookDao bookDao = new BookDaoImpl();
		Set<Payway> payways = bookDao.getAllPayways();

		// 放进request中
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("payways", payways);
		request.setAttribute("order_id", order_id);

		// 跳转到选择支付方式页面
		request.getRequestDispatcher("WEB-INF/bookshop/payway.jsp").forward(
				request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
