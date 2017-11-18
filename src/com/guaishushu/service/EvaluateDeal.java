package com.guaishushu.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.guaishushu.bean.Evaluate;
import com.guaishushu.bean.User;
import com.guaishushu.dao.EvaluateDao;
import com.guaishushu.dao.EvaluateDaoImpl;
import com.guaishushu.dao.OrderDao;
import com.guaishushu.dao.OrderDaoImpl;

/**
 * 对评价的一些操作 添加评论,删除评论
 * 
 * @author 付旺辉
 * @date 2017年6月15日
 * @time 下午2:50:10
 */
public class EvaluateDeal extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		EvaluateDao evaluateDao = new EvaluateDaoImpl();
		OrderDao orderDao = new OrderDaoImpl();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("login_user");

		// 获取用户操作的类型
		String type = request.getParameter("type");

		if ("addEvaluate".equals(type)) {
			// 如果是添加评价
			// 获取订单号
			long order_id = Long.parseLong(request.getParameter("order_id"));

			// 获取所有的textarea中的数据和对应的评价图书id,以及评分
			String[] textareas = request.getParameterValues("mytextarea");
			String[] bookIds = request.getParameterValues("book_id");
			String[] scores = request.getParameterValues("raty_score");
			String[] versions = request.getParameterValues("book_version");

			// 获取条数
			System.out.println("本次添加的评价条数 : " + bookIds.length);

			for (int i = 0; i < bookIds.length; i++) {

				// 向评价表中插入数据
				Evaluate evaluate = new Evaluate(null,
						Integer.parseInt(scores[i]),
						Integer.parseInt(versions[i]), textareas[i]);

				evaluateDao.addEvaluate(evaluate, user.getId(),
						Long.parseLong(bookIds[i]));

			}
			// 更新订单的状态
			orderDao.updateOrderStatus(order_id, "3");

			// 添加评价成功,跳转到查看当前订单的页面
			response.sendRedirect("/MyBookShop/ToShowOrder?type=single&order_id="
					+ order_id);

		} else if ("deleteEvaluate".equals(type)) {
			// 如果是删除评价
			// 获取要删除的评价的id
			long evaluate_id = Long.parseLong(request
					.getParameter("evaluate_id"));
			// 删除评价
			evaluateDao.deleteEvaluateById(evaluate_id);

			// 再跳回查看评价的页面
			response.sendRedirect("/MyBookShop/ToShowMyEvaluate?type=all");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
