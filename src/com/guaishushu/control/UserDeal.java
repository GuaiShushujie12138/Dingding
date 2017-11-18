package com.guaishushu.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guaishushu.bean.User;
import com.guaishushu.dao.UserDao;
import com.guaishushu.dao.UserDaoImpl;

/**
 * 对用户信息修改的操作
 * 
 * @author 付旺辉
 * @date 2017年6月4日
 * @time 上午10:09:23
 */
public class UserDeal extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		UserDao userDao = new UserDaoImpl();
		// 获取用户的更新信息
		long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		String zip = request.getParameter("zip");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");

		User user = new User(passwd, zip, address, phone, email);

		// 更新用户数据
		userDao.updateUser(id, user);

		// 更新session中的用户
		User newUser = userDao.findUserByName(name);
		request.getSession().setAttribute("login_user", newUser);

		// 跳转到首页
		response.sendRedirect("/MyBookShop/ToIndex");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
