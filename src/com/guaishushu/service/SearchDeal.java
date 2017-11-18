package com.guaishushu.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guaishushu.dao.BookDao;
import com.guaishushu.dao.BookDaoImpl;

/*
 * 对搜索的一些处理
 */
public class SearchDeal extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		BookDao bookDao = new BookDaoImpl();

		// 判断用户要操作的类型
		String type = request.getParameter("type");
		if ("jump".equals(type)) {
			// 如果是搜索直接跳转到图书详情页
			String bookName = request.getParameter("bookname").trim();
			long book_id = bookDao.findIdByBookName(bookName);

			// System.out.println("书名 :" + bookName);

			if (book_id == -1) {
				response.sendRedirect("/MyBookShop/ToIndex");
			} else {
				// 跳转到该图书的详情页
				response.sendRedirect("/MyBookShop/ToBookView?bookId="
						+ book_id);
			}

		} else if ("ajax".equals(type)) {
			// 如果是需要获取ajax数据
			// 获取输入框中的字符串
			String str = request.getParameter("str");
			List<String> bookNames = bookDao.findBookNameByString(str);

			StringBuilder sb = new StringBuilder();

			if (bookNames != null && bookNames.size() > 0) {
				for (int i = 0; i < bookNames.size(); i++) {
					// 最多取出三个书名
					if (i > 2) {
						break;
					}
					if (i != 0) {
						sb.append("," + bookNames.get(i));
					} else if (i == 0) {
						sb.append(bookNames.get(i));
					}
				}
			}

			// 返回数据给ajax
			out.write(sb.toString());
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
