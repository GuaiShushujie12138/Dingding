package com.guaishushu.control;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guaishushu.bean.Evaluate;
import com.guaishushu.bean.Product;
import com.guaishushu.dao.BookDao;
import com.guaishushu.dao.BookDaoImpl;
import com.guaishushu.dao.EvaluateDao;
import com.guaishushu.dao.EvaluateDaoImpl;

/**
 * 展示图书所有的评价
 * 
 * @author 付旺辉
 * @date 2017年6月16日
 * @time 上午11:14:57
 */
public class ToShowBookEvaluate extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		EvaluateDao evaluateDao = new EvaluateDaoImpl();
		BookDao bookDao = new BookDaoImpl();

		// 获取要展示图书的id
		long book_id = Long.parseLong(request.getParameter("book_id"));
		// 获取该书的全部评价
		Set<Evaluate> evaluates = evaluateDao.findEvaluatesByBookId(book_id);
		Product book = bookDao.findBookById(book_id);

		request.setAttribute("evaluates", evaluates);
		request.setAttribute("book", book);
		request.setAttribute("size", evaluates.size());

		request.getRequestDispatcher("WEB-INF/bookshop/showbookevaluate.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
