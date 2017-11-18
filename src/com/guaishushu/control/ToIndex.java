package com.guaishushu.control;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.guaishushu.bean.Category;
import com.guaishushu.bean.Product;
import com.guaishushu.bean.Report;
import com.guaishushu.dao.BookDao;
import com.guaishushu.dao.BookDaoImpl;
import com.guaishushu.dao.CategoryDao;
import com.guaishushu.dao.CategoryDaoImpl;

public class ToIndex extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		BookDao bookDao = new BookDaoImpl();
		HttpSession session = request.getSession();
		CategoryDao categoryDao = new CategoryDaoImpl();

		// 判断用户当前的操作,是不是要退出了
		String type = request.getParameter("type");
		if ("exit".equals(type)) {
			// 用户要安全退出了,把session中的用户数据清空
			session.removeAttribute("login_user");
			session.removeAttribute("car");
		}

		// 判断当前有没有把所有的图书信息存放在servletContext中
		if (session.getServletContext().getAttribute("all_books") == null) {
			// 说明是第一次进入首页,查询出所有的图书信息,并放进servletContext中
			Map<Long, Category> all_books = categoryDao.findAllCategorys();
			TreeSet<Category> categories = new TreeSet<Category>();
			Iterator<Category> it = all_books.values().iterator();

			while (it.hasNext()) {
				categories.add(it.next());
			}

			// 取出所有的简报
			Set<Report> reports = bookDao.findAllReport();

			session.getServletContext().setAttribute("all_books", categories);
			session.getServletContext().setAttribute("reports", reports);
		}

		// 取出所有图书分类
		TreeSet<Category> categories = (TreeSet<Category>) session
				.getServletContext().getAttribute("all_books");

		// 查询热卖的图书(最多显示三本)
		if (session.getServletContext().getAttribute("hot_books") == null) {
			Set<Product> books = bookDao.findHotBooks(2);
			Set<Product> hot_books = new TreeSet<Product>();
			int flag = 0;

			Iterator<Product> it1 = books.iterator();
			while (it1.hasNext()) {
				flag++;
				Product book = it1.next();
				hot_books.add(book);

				if (flag >= 3) {
					break;
				}
			}

			// 存进ServletContext中
			request.getServletContext().setAttribute("hot_books", hot_books);
		}

		// 查询特别推荐的图书(最多显示九本)
		if (session.getServletContext().getAttribute("special_books") == null) {
			Set<Product> books = bookDao.findHotBooks(1);
			Set<Product> special_books = new TreeSet<Product>();
			int flag = 0;

			Iterator<Product> it1 = books.iterator();
			while (it1.hasNext()) {
				flag++;
				Product book = it1.next();
				special_books.add(book);

				if (flag >= 9) {
					break;
				}
			}

			// 存进ServletContext中
			request.getServletContext().setAttribute("special_books",
					special_books);
		}

		// 放进request中
		request.setAttribute("categories", categories);
		request.setAttribute("hot_books", request.getServletContext()
				.getAttribute("hot_books"));

		// 跳转到jsp页面
		request.getRequestDispatcher("/WEB-INF/bookshop/index.jsp").forward(
				request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
