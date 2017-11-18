package com.guaishushu.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToShopCar extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		// 判断用户是否登录了
		if (request.getSession().getAttribute("login_user") == null) {
			response.sendRedirect("/MyBookShop/ToLogin");
		} else {
			// 跳转到购物车页面
			request.getRequestDispatcher("WEB-INF/bookshop/showcar.jsp")
					.forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
