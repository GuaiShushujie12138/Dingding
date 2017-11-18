package com.guaishushu.control;

import java.awt.print.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.guaishushu.bean.OrderLine;
import com.guaishushu.bean.Product;
import com.guaishushu.bean.ShopCar;
import com.guaishushu.dao.BookDao;
import com.guaishushu.dao.BookDaoImpl;

public class BookDeal extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Book> list = null;

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		long bookId = Long.parseLong(request.getParameter("bookId"));
		ShopCar car = (ShopCar) request.getSession().getAttribute("car");
		BookDao bookDao = new BookDaoImpl();

		// System.out.println("BookDeal : 操作前的car产品数量 : " +
		// car.getMap().size());

		// 判断用户当前操作,是添加图书还是减少图书还是删除图书
		String type = request.getParameter("type");

		if ("update".equals(type)) {
			int number = Integer.parseInt(request.getParameter("bookNum"));
			JSONObject jsonObject = new JSONObject();
			// 图书版本
			int version = Integer.parseInt(request.getParameter("version"));
			// 添加图书或者减少图书(统一是更新图书)
			try {
				car.updateProduct(bookId, number, version);
				// System.out.println("BookDeal : 操作后的car产品数量 : "
				// + car.getMap().size());
			} catch (Exception e) {
				e.printStackTrace();
			}

			OrderLine orderLine = car.getMap().get(bookId + "" + version);
			if (number > 0) {
				jsonObject.put("totalPrice", orderLine.getA_price() * number);
			}

			out.println(jsonObject);
		} else if ("delete".equals(type)) {
			// 删除图书
			// 图书版本
			int version = Integer.parseInt(request.getParameter("version"));
			try {
				car.removeProduct(bookId, version);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("addBook".equals(type)) {
			JSONObject jsonObject = new JSONObject();
			// 把图书加入购物车
			if (request.getSession().getAttribute("login_user") == null) {
				jsonObject.put("flag", false);
			} else {
				// 根据id查找图书
				Product book = bookDao.findBookById(bookId);
				// 获取版本号
				String version_msg = request.getParameter("version");
				// System.out.println("version_msg : " + version_msg);
				int version = Integer.parseInt(version_msg);
				// 获取数量
				int bookNum = 1;
				String numMsg = request.getParameter("bookNum");
				if (numMsg != null) {
					bookNum = Integer.parseInt(numMsg);
				}

				// 根据版本号查找利率
				double rate = bookDao.getRate(version);
				// 修改图书的价格信息
				book.setPrice(book.getPrice() * rate);
				// 修改图书的版本
				book.setVersion(version);

				// 登录了那么就把图书添加进购物车
				try {
					for (int i = 0; i < bookNum; i++) {
						car.addProduct(book);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 把消息传回给js
				jsonObject.put("flag", true);
			}
			out.println(jsonObject);
		} else if ("getVersionPrice".equals(type)) {
			// 如果是从js中发过来想要获取图书版本的价格的请求
			// 获取版本号
			int version = Integer.parseInt(request.getParameter("version"));

			JSONObject jsonObject = new JSONObject();

			// 根据id查找book
			Product book = bookDao.findBookById(bookId);
			// 根据版本号查找利率
			double rate = bookDao.getRate(version);

			double price = book.getPrice() * rate;
			jsonObject.put("price", price);

			out.println(jsonObject);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
