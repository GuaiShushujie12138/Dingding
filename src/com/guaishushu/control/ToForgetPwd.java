package com.guaishushu.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToForgetPwd extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		String step = request.getParameter("step");

		if (step == null || "".equals(step)) {
			// 说明是从登陆界面开始点击的忘记密码
			// 获取下从忘记密码界面传过来的用户名
			String name = request.getParameter("username");
			if (name == null) {
				name = "";
			}
			// 放进request中
			request.setAttribute("username", name);

			request.getRequestDispatcher("WEB-INF/bookshop/forgetpwd.jsp")
					.forward(request, response);
		} else if ("step1".equals(step)) {
			// 进入下一步操作
			request.getRequestDispatcher("WEB-INF/bookshop/forgetpwd1.jsp")
					.forward(request, response);
		} else if ("step2".equals(step)) {
			// 进入下一步操作
			request.getRequestDispatcher("WEB-INF/bookshop/forgetpwd2.jsp")
					.forward(request, response);
		} else if ("step3".equals(step)) {
			// 进入下一步操作
			request.getRequestDispatcher("WEB-INF/bookshop/forgetpwd3.jsp")
					.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
