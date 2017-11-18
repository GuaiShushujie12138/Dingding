package com.guaishushu.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.guaishushu.bean.Product;
import com.guaishushu.bean.ShopCar;
import com.guaishushu.bean.User;
import com.guaishushu.dao.BookDao;
import com.guaishushu.dao.BookDaoImpl;
import com.guaishushu.dao.UserDao;
import com.guaishushu.dao.UserDaoImpl;

/**
 * 检查用户的登录和注册动作
 * 
 * @author 付旺辉
 * @date 2017年5月31日
 * @time 下午8:07:24
 */
public class UserCheck extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		// 获取传递过来的类型,判断用户是注册还是登录
		String type = request.getParameter("type");
		// System.out.println(type);
		UserDao userDao = new UserDaoImpl();

		if ("register".equals(type)) {
			// 如果用户是要注册
			// 获取用户注册信息
			String name = request.getParameter("username");
			String passwd = request.getParameter("passwd");
			String zip = request.getParameter("zip");
			String address = request.getParameter("address");
			String telephone = request.getParameter("telephone");
			String email = request.getParameter("email");

			User user = new User(name, passwd, zip, address, telephone, email);

			User user2 = userDao.findUserByName(name);
			if (user2 == null) {
				// 说明可以注册,添加用户信息
				userDao.addUser(user);

				// 注册成功,跳转到登录界面
				response.sendRedirect("/MyBookShop/ToLogin");
			} else {
				// 用户名已被注册,返回注册页面
				// 携带错误信息
				request.setAttribute("info", "该用户名已被注册!");
				request.getRequestDispatcher("/ToRegister").forward(request,
						response);
			}
		} else if ("login".equals(type)) {
			// 如果用户是要登录
			// 获取用户信息
			String name = request.getParameter("username");
			String passwd = request.getParameter("passwd");

			User user = new User(name, passwd);
			// 判断用户是否登陆成功
			if (userDao.isExits(user)) {
				// 用户登陆成功,补全用户信息
				user = userDao.findUserByName(name);
				// 创建购物车
				ShopCar car = new ShopCar();

				// 把用户信息保存到session
				session.setAttribute("login_user", user);
				// 保存用户购物车信息
				session.setAttribute("car", car);
				// System.out.println("UserCheck user: " + user);

				// 跳转到首页
				response.sendRedirect("/MyBookShop/ToIndex");
			} else {
				// 用户名或密码错误
				// 跳回到登录页面,携带错误信息
				request.setAttribute("info", "用户名或者密码错误!");
				request.getRequestDispatcher("/ToLogin").forward(request,
						response);
			}
		} else if ("usernameCheck".equals(type)) {
			// 如果是从js中发送过来的验证用户名是否存在
			String name = request.getParameter("username");
			User user = userDao.findUserByName(name);
			// 使用json传输数据
			JSONObject jsonObject = new JSONObject();
			if (user == null) {
				// 可以注册
				jsonObject.put("flag", true);
			} else {
				jsonObject.put("flag", false);
			}
			out.println(jsonObject);
		} else if ("checkcode".equals(type)) {
			// 如果是从js中发送过来的验证验证码是否填写正确
			String checkcode = request.getParameter("checkcode");
			String checkcode_sure = (String) session.getAttribute("checkcode");
			JSONObject jsonObject = new JSONObject();

			if (checkcode != null && !"".equals(checkcode)
					&& checkcode.equals(checkcode_sure)) {
				// 验证码填写正确
				jsonObject.put("flag", true);
			} else {
				jsonObject.put("flag", false);
			}
			out.println(jsonObject);
		} else if ("isLogin".equals(type)) {
			JSONObject jsonObject = new JSONObject();

			// 如果是从js中传递过来的判断用户是否登录
			if (session.getAttribute("login_user") != null) {
				// 说明用户登录了,可以执行添加购物车操作

				long bookId = Long.parseLong(request.getParameter("bookId"));
				// 根据图书id查询图书
				BookDao bookDao = new BookDaoImpl();
				Product book = bookDao.findBookById(bookId);

				// 把图书加入购物车中
				ShopCar car = (ShopCar) session.getAttribute("car");
				System.out.println("car : " + car);
				try {
					car.addProduct(book);
				} catch (Exception e) {
					e.printStackTrace();
				}

				jsonObject.put("flag", true);
			} else {
				jsonObject.put("flag", false);
			}

			out.println(jsonObject);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
