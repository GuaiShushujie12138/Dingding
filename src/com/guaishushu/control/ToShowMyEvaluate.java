package com.guaishushu.control;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guaishushu.bean.Evaluate;
import com.guaishushu.bean.User;
import com.guaishushu.dao.EvaluateDao;
import com.guaishushu.dao.EvaluateDaoImpl;

/**
 * 展示评价
 * 
 * @author 付旺辉
 * @date 2017年6月14日
 * @time 下午8:27:38
 */
public class ToShowMyEvaluate extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		EvaluateDao evaluateDao = new EvaluateDaoImpl();
		long user_id = ((User) request.getSession().getAttribute("login_user"))
				.getId();

		// 获取用户当前所要的操作,是查看一个订单的评论还是查看该用户所有的评论
		String type = request.getParameter("type");
		Set<Evaluate> evaluates = null;

		if ("single".equals(type)) {
			// 如果是查看当前订单的评价
			// 获取订单号
			long order_id = Long.parseLong(request.getParameter("order_id"));
			// 根据订单号查询评价
			evaluates = evaluateDao.findEvaluatesByOrderId(order_id, user_id);

			// // 测试
			// Iterator<Evaluate> it = evaluates.iterator();
			// while (it.hasNext()) {
			// System.out.println(it.next());
			// }

			// 放进request中
			request.setAttribute("evaluates", evaluates);

			request.getRequestDispatcher("WEB-INF/bookshop/showmyevaluate.jsp")
					.forward(request, response);
		} else if ("all".equals(type)) {
			// 如果是查询用户的所有评价

			evaluates = evaluateDao.findEvaluatesByUserId(user_id);

			// 放进request中
			request.setAttribute("evaluates", evaluates);

			request.getRequestDispatcher("WEB-INF/bookshop/showmyevaluate.jsp")
					.forward(request, response);
		} else if ("book".equals(type)) {
			// 获取图书id
			long book_id = Long.parseLong(request.getParameter("book_id"));

			// 如果是要查询图书的所有评价
			evaluates = evaluateDao.findEvaluatesByBookId(book_id);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
