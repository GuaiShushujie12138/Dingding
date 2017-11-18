package com.guaishushu.control;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guaishushu.bean.OrderLine;
import com.guaishushu.bean.Product;
import com.guaishushu.bean.Receiver;
import com.guaishushu.bean.ShopCar;
import com.guaishushu.bean.User;
import com.guaishushu.dao.BookDao;
import com.guaishushu.dao.BookDaoImpl;
import com.guaishushu.dao.OrderDao;
import com.guaishushu.dao.OrderDaoImpl;
import com.guaishushu.dao.ReceiverDao;
import com.guaishushu.dao.ReceiverDaoImpl;

public class ToConfirm extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		// 判断用户当前是去结算还是已经提交订单了
		String type = request.getParameter("type");

		if ("count".equals(type)) {
			// 如果是去结算
			// 获取购物车中选中的图书id信息
			String bookIds = request.getParameter("bookIds");
			Set<OrderLine> orderLines = new TreeSet<OrderLine>();
			ShopCar car = (ShopCar) request.getSession().getAttribute("car");

			if (bookIds != null) {
				String[] msgs = bookIds.split(",");

				for (int i = 0; i < msgs.length; i++) {
					// System.out.println("msg : " + msgs[i]);
					String[] msg = msgs[i].trim().split("=");
					String id = msg[0];
					String ver = msg[1];
					long bookId = Long.parseLong(id);
					int version = Integer.parseInt(ver);

					// 根据bookid和version查找购物车中的orderline
					OrderLine orderLine = car.getMap().get(
							bookId + "" + version);
					orderLines.add(orderLine);
				}

			}

			// 存放进request中
			request.setAttribute("orderLines", orderLines);
			request.setAttribute("bookIds", bookIds);

			// 跳转到确认订单页面
			request.getRequestDispatcher("WEB-INF/bookshop/confirm.jsp")
					.forward(request, response);

		} else if ("submit".equals(type)) {
			// 如果是提交订单,选择付款方式,这个时候要向订单表和订单项表中插入数据,生成订单
			// 获取总价
			String totalPrice = request.getParameter("totalPrice");
			String bookIds = request.getParameter("bookIds");
			// 获取收货人信息
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String tel = request.getParameter("tel");

			Receiver receiver = new Receiver(name, address, tel);
			long user_id = ((User) request.getSession().getAttribute(
					"login_user")).getId();

			// 根据收货人信息从数据库中查找收货人id,如果查不到就插入数据
			ReceiverDao receiverDao = new ReceiverDaoImpl();
			long receiver_id = receiverDao.findReceiverIdByInfo(receiver,
					user_id);

			if (receiver_id >= 0) {
				// 表示找到了
			} else {
				// 如果数据库没有的话,就要插入数据
				receiverDao.addReceiver(receiver, user_id);
				// 再次查找到当前插入数据库的收件人id
				receiver_id = receiverDao.findReceiverIdByInfo(receiver,
						user_id);
			}

			/* 更新数据库数据 */
			// 查找购物车
			ShopCar car = (ShopCar) request.getSession().getAttribute("car");
			// 订单编号
			long order_id = 0;

			if (bookIds != null) {
				// 所有订单项
				Set<OrderLine> orderLines = new TreeSet<OrderLine>();

				String[] msgs = bookIds.split(",");

				for (int i = 0; i < msgs.length; i++) {
					String[] msg = msgs[i].split("=");
					String id = msg[0];
					String ver = msg[1];
					long bookId = Long.parseLong(id);
					int version = Integer.parseInt(ver);

					// 根据bookId查找订单项集合
					OrderLine orderLine = car.getMap().get(
							bookId + "" + version);
					orderLines.add(orderLine);

				}

				// 向订单表、订单项表中插入数据,以及更新图书库存,并获取订单id
				OrderDao orderDao = new OrderDaoImpl();
				order_id = orderDao.insertDataToOrderAndLine(orderLines,
						receiver_id, user_id, 0);// 最后的参数代表的是付款方式,当前订单状态为待付款

				Iterator<OrderLine> it = orderLines.iterator();
				while (it.hasNext()) {
					OrderLine orderLine = it.next();
					// 提交订单完毕,把购物车中数据移除
					try {
						car.removeProduct(orderLine.getProduct().getId(),
								orderLine.getProduct_version());
					} catch (Exception e) {
						System.out.println("购物车数据删除失败!");
						e.printStackTrace();
					}
				}
			}

			// 跳转到选择支付方式的页面
			response.sendRedirect("/MyBookShop/ToPayWay?totalPrice="
					+ totalPrice + "&order_id=" + order_id);
		} else if ("pay".equals(type)) {
			// 如果是支付按钮
			// 获取用户支付方式
			long payway_id = Long.parseLong(request.getParameter("payway"));

			// 获取订单编号
			long order_id = Long.parseLong(request.getParameter("order_id"));

			// 更新订单表以及图书库存
			OrderDao orderDao = new OrderDaoImpl();
			String status = "2";// 2状态代表支付完成
			orderDao.updateOrderStatus(order_id, "2", payway_id);
			System.out.println("订单状态更新完毕!");

			// 根据订单号查询所有订单项
			Set<OrderLine> orderLines = orderDao
					.findAllOrderLinesByOrderId(order_id);

			// 更新图书库存
			BookDao bookDao = new BookDaoImpl();
			Iterator<OrderLine> it = orderLines.iterator();
			while (it.hasNext()) {
				OrderLine orderLine = it.next();
				Product book = orderLine.getProduct();
				long stock = book.getStock();
				long bookId = book.getId();
				long amount = orderLine.getAmount();// 订单项中的图书数量
				long sale_num = book.getSale_num();
				sale_num += amount;

				if (amount > stock) {
					// 库存不足
					throw new RuntimeException("库存不足!");
				}

				bookDao.updateBookStock(bookId, stock, sale_num);
			}
			System.out.println("图书库存更新完毕!");

			// 跳转到订单生成完毕的页面
			response.sendRedirect("/MyBookShop/ToFinish?order_id=" + order_id);

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
