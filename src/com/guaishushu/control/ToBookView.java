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

import com.guaishushu.bean.Category;
import com.guaishushu.bean.Evaluate;
import com.guaishushu.bean.Product;
import com.guaishushu.dao.BookDao;
import com.guaishushu.dao.BookDaoImpl;
import com.guaishushu.dao.CategoryDao;
import com.guaishushu.dao.CategoryDaoImpl;
import com.guaishushu.dao.EvaluateDao;
import com.guaishushu.dao.EvaluateDaoImpl;

public class ToBookView extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		BookDao bookDao = new BookDaoImpl();
		CategoryDao categoryDao = new CategoryDaoImpl();
		EvaluateDao evaluateDao = new EvaluateDaoImpl();

		// 查询所有图书分类
		Map<Long, Category> map = categoryDao.findAllCategorys();
		Iterator<Category> it = map.values().iterator();
		Set<Category> categories = new TreeSet<Category>();

		while (it.hasNext()) {
			Category category = it.next();

			categories.add(category);
		}

		// 查询要显示的图书
		long bookId = Long.parseLong(request.getParameter("bookId"));
		Product book = bookDao.findBookById(bookId);

		// 查询当前图书所属的大分类
		Category category = categoryDao.findCategoryByBookId(bookId);

		// 查询出一条最近的图书评价
		Set<Evaluate> evaluates = evaluateDao.findEvaluatesByBookId(bookId);
		Iterator<Evaluate> it_evaluate = evaluates.iterator();
		Evaluate evaluate = null;
		String book_version = null;
		while (it_evaluate.hasNext()) {
			evaluate = it_evaluate.next();
			int version = evaluate.getProduct_version();
			switch (version) {
			case 1:
				book_version = "精装版";
				break;
			case 2:
				book_version = "简装版";
				break;
			case 3:
				book_version = "收藏版";
				break;
			}

			break;
		}

		// 存放进request中
		request.setAttribute("book", book);
		request.setAttribute("categories", categories);
		request.setAttribute("category", category);
		request.setAttribute("evaluate", evaluate);
		request.setAttribute("size", evaluates.size());
		request.setAttribute("book_version", book_version);

		// 跳转到图书详情页
		request.getRequestDispatcher("WEB-INF/bookshop/viewbook.jsp").forward(
				request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
