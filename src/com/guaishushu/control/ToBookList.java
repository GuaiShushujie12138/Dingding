package com.guaishushu.control;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.guaishushu.bean.Category;
import com.guaishushu.bean.CategoryDetail;
import com.guaishushu.bean.PriceRank;
import com.guaishushu.bean.Product;
import com.guaishushu.bean.Publish;
import com.guaishushu.dao.BookDao;
import com.guaishushu.dao.BookDaoImpl;
import com.guaishushu.dao.CategoryDao;
import com.guaishushu.dao.CategoryDaoImpl;
import com.guaishushu.sort.BookDateComparator;
import com.guaishushu.sort.BookPriceComparator;
import com.guaishushu.sort.BookSaleNumComparator;

public class ToBookList extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		BookDao bookDao = new BookDaoImpl();
		CategoryDao categoryDao = new CategoryDaoImpl();

		// 判断用户点击的是大的图书分类还是小的图书分类
		String type = request.getParameter("type");
		HttpSession session = request.getSession();

		// 查询所有的出版社
		Set<Publish> publishs = bookDao.findAllPublish();

		// 查询所有的图书价格区间(PriceRank)
		Set<PriceRank> ranks = bookDao.findallPriceRanks();

		// 查询热卖的图书(最多显示四本)
		Set<Product> set = bookDao.findHotBooks(2);
		Set<Product> hot_books = new TreeSet<Product>();
		int flag = 0;

		Iterator<Product> it1 = set.iterator();
		while (it1.hasNext()) {
			flag++;
			Product book = it1.next();
			hot_books.add(book);

			if (flag >= 4) {
				break;
			}
		}

		// 把热卖图书放进request中
		request.setAttribute("hot_books", hot_books);
		// 把出版社放进request中
		request.setAttribute("publishs", publishs);
		request.setAttribute("ranks", ranks);

		// 分类名字
		// 大分类
		String categoryName = "";
		// 小分类
		String detailName = "";

		/**
		 * 分页1
		 */
		// 获取当前页的页码数
		int pageNow = 1;// 默认是第一页
		String pageNowMsg = request.getParameter("pageNow");
		if (pageNowMsg != null && !"".equals(pageNowMsg)
				&& !"null".equals(pageNowMsg)
				&& !"undefined".equals(pageNowMsg)) {
			pageNow = Integer.parseInt(pageNowMsg);
		}
		// 设置一个页面显示的图书条数
		int pageSize = 6;
		// 查询当前条件下的图书总数
		int counts = 0;
		// 页面总数
		int pageCount = 0;

		// booklist中的图书
		Set<Product> books = null;

		// 获取筛选的条件
		String rankMsg = request.getParameter("rank");
		String publishMsg = request.getParameter("publish");

		System.out.println("rankMsg : " + rankMsg);
		System.out.println("publishMsg : " + publishMsg);
		long rank_id = -1;
		long publish_id = -1;

		if (rankMsg != null && !"".equals(rankMsg) && !"null".equals(rankMsg)) {
			rank_id = Long.parseLong(rankMsg);
		}

		if (publishMsg != null && !"".equals(publishMsg)
				&& !"null".equals(publishMsg)) {
			publish_id = Long.parseLong(publishMsg);
		}

		long type_id = 0;

		if ("category".equals(type)) {
			type_id = Long.parseLong(request.getParameter("category_id"));
			Category category = categoryDao.findCategoryByCategoryId(type_id);
			categoryName = category.getName();
			detailName = categoryName;
		} else if ("detail".equals(type)) {
			type_id = Long.parseLong(request.getParameter("detail_id"));
			CategoryDetail detail = categoryDao.findDetailByDetailId(type_id);
			Category category = categoryDao.findCategoryByCategoryId(detail
					.getCategory().getId());
			categoryName = category.getName();
			detailName = detail.getName();
		}

		// 把大小分类的名字放进request中
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("detailName", detailName);

		// if ("category".equals(type)) {
		// // 大分类
		// long category_id = Long.parseLong(request
		// .getParameter("category_id"));
		//
		// if (rank_id != -1 && publish_id != -1) {
		// books = bookDao.findBooksByCategoryIdAndPublishAndRank(
		// category_id, publish_id, rank_id);
		// } else if (rank_id != -1 && publish_id == -1) {
		// books = bookDao.findBooksByCategoryIdAndRank(category_id,
		// rank_id);
		// } else if (rank_id == -1 && publish_id != -1) {
		// books = bookDao.findBooksByCategoryIdAndPublish(category_id,
		// publish_id);
		// } else {
		// books = bookDao.findBooksByCategoryId(category_id);
		// }
		//
		// } else if ("detail".equals(type)) {
		// // 小分类
		// long detail_id = Long.parseLong(request.getParameter("detail_id"));
		//
		// if (rank_id != -1 && publish_id != -1) {
		// books = bookDao.findBooksByDetailIdAndPublishAndRank(detail_id,
		// publish_id, rank_id);
		// } else if (rank_id != -1 && publish_id == -1) {
		// books = bookDao.findBooksByDetailIdAndRank(detail_id, rank_id);
		// } else if (rank_id == -1 && publish_id != -1) {
		// books = bookDao.findBooksByDetailIdAndPublish(detail_id,
		// publish_id);
		// } else {
		// books = bookDao.findBooksByDetailId(detail_id);
		// }
		//
		// }

		// 查询出所有的图书来
		books = bookDao.findAllBooks(type, type_id, rank_id, publish_id);

		// 判断是否还有其他附加条件
		// 排序条件
		String sort = request.getParameter("sort");
		System.out.println("sort : " + sort);
		Iterator<Product> it = books.iterator();
		if ("sale_num".equals(sort)) {
			// 如果是按照销量排序
			Set<Product> saleBooks = new TreeSet<Product>(
					new BookSaleNumComparator());
			while (it.hasNext()) {
				saleBooks.add(it.next());
			}
			books = saleBooks;

			/**
			 * 分页2.1
			 */
			// 图书总数
			counts = books.size();
			// 页面数
			pageCount = (counts + pageSize - 1) / pageSize;
			Set<Product> booksNow = new TreeSet<Product>(
					new BookSaleNumComparator());

			Iterator<Product> it_old = books.iterator();
			int i = 0;
			while (it_old.hasNext()) {
				i++;
				if (i > (pageNow - 1) * pageSize && i <= pageNow * pageSize) {
					booksNow.add(it_old.next());
				} else {
					it_old.next();
				}
			}
			books = booksNow;

		} else if ("price".equals(sort)) {
			// 如果是按照价格排序
			Set<Product> saleBooks = new TreeSet<Product>(
					new BookPriceComparator());
			while (it.hasNext()) {
				saleBooks.add(it.next());
			}
			books = saleBooks;

			/**
			 * 分页2.2
			 */
			// 图书总数
			counts = books.size();
			// 页面数
			pageCount = (counts + pageSize - 1) / pageSize;
			Set<Product> booksNow = new TreeSet<Product>(
					new BookPriceComparator());

			Iterator<Product> it_old = books.iterator();
			int i = 0;
			while (it_old.hasNext()) {
				i++;
				if (i > (pageNow - 1) * pageSize && i <= pageNow * pageSize) {
					booksNow.add(it_old.next());
				} else {
					it_old.next();
				}
			}
			books = booksNow;

		} else if ("new_product".equals(sort)) {
			// 如果是按照出版日期排序
			Set<Product> saleBooks = new TreeSet<Product>(
					new BookDateComparator());
			while (it.hasNext()) {
				saleBooks.add(it.next());
			}
			books = saleBooks;

			/**
			 * 分页2.3
			 */
			// 图书总数
			counts = books.size();
			// 页面数
			pageCount = (counts + pageSize - 1) / pageSize;
			Set<Product> booksNow = new TreeSet<Product>(
					new BookDateComparator());

			Iterator<Product> it_old = books.iterator();
			int i = 0;
			while (it_old.hasNext()) {
				i++;
				if (i > (pageNow - 1) * pageSize && i <= pageNow * pageSize) {
					booksNow.add(it_old.next());
				} else {
					it_old.next();
				}
			}
			books = booksNow;

		} else {
			// 默认排序

			/**
			 * 分页2.4
			 */
			// 图书总数
			counts = books.size();
			System.out.println("counts : " + counts);
			// 页面数
			pageCount = (counts + pageSize - 1) / pageSize;
			System.out.println("pageCount : " + pageCount);
			Set<Product> booksNow = new TreeSet<Product>();

			Iterator<Product> it_old = books.iterator();
			int i = 0;
			while (it_old.hasNext()) {
				i++;
				if (i > (pageNow - 1) * pageSize && i <= pageNow * pageSize) {
					booksNow.add(it_old.next());
				} else {
					it_old.next();
				}
			}
			books = booksNow;

		}

		/**
		 * 分页3
		 */
		// 把页面总数和当前页放进request中
		request.setAttribute("pageNow", pageNow);
		request.setAttribute("pageCount", pageCount);

		// 存放图书到request中
		request.setAttribute("books", books);

		// 跳转
		request.getRequestDispatcher("WEB-INF/bookshop/booklist.jsp").forward(
				request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
