package com.guaishushu.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.guaishushu.bean.Evaluate;
import com.guaishushu.bean.OrderLine;
import com.guaishushu.bean.Product;
import com.guaishushu.bean.User;
import com.guaishushu.db.SQLHelper;

public class EvaluateDaoImpl implements EvaluateDao {
	private SQLHelper helper;

	@Override
	/* 添加评价 */
	public void addEvaluate(Evaluate evaluate, long user_id, long book_id) {
		helper = new SQLHelper();

		String sql = "insert into d_evaluate values (d_evaluate_seq.nextVal, ?, sysdate, ?, ?, ?, ?, ?)";
		Object[] params = { evaluate.getImages(), evaluate.getState(),
				evaluate.getContent(), user_id, book_id,
				evaluate.getProduct_version() };
		helper.insert(sql, params);
	}

	@Override
	/* 通过评价id删除评价 */
	public void deleteEvaluateById(long evaluate_id) {
		helper = new SQLHelper();

		String sql = "delete from d_evaluate where id = ?";
		Long[] params = { evaluate_id };
		helper.delete(sql, params);
	}

	@Override
	/* 通过用户id查询用户所有的评价 */
	public Set<Evaluate> findEvaluatesByUserId(long user_id) {
		helper = new SQLHelper();
		Set<Evaluate> evaluates = new TreeSet<Evaluate>();

		String sql = "select * from d_evaluate where user_id = ?";
		Long[] params = { user_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;
		Evaluate evaluate = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				evaluate = getEvaluate(map);
				evaluates.add(evaluate);
			}
		}

		return evaluates;
	}

	@Override
	/* 通过图书id查询所有的有关该图书的评价 */
	public Set<Evaluate> findEvaluatesByBookId(long book_id) {
		Set<Evaluate> evaluates = new TreeSet<Evaluate>();
		helper = new SQLHelper();

		String sql = "select * from d_evaluate where product_id = ?";
		Long[] params = { book_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;
		Evaluate evaluate = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				evaluate = getEvaluate(map);
				evaluates.add(evaluate);
			}
		}

		return evaluates;
	}

	@Override
	/* 通过订单号和用户id查询评价 */
	public Set<Evaluate> findEvaluatesByOrderId(long order_id, long user_id) {
		helper = new SQLHelper();
		Set<Evaluate> evaluates = new TreeSet<Evaluate>();

		// 通过订单号查找所有的订单项
		OrderDao orderDao = new OrderDaoImpl();
		Set<OrderLine> orderLines = orderDao
				.findAllOrderLinesByOrderId(order_id);
		Iterator<OrderLine> it = orderLines.iterator();
		Evaluate evaluate = null;

		while (it.hasNext()) {
			OrderLine orderLine = it.next();
			long book_id = orderLine.getProduct().getId();

			String sql = "select * from d_evaluate where user_id = ? and product_id = ?";
			Long[] params = { user_id, book_id };
			List<Map<String, Object>> list = helper.query(sql, params);
			Map<String, Object> map = null;

			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					map = list.get(i);

					evaluate = getEvaluate(map);
					evaluates.add(evaluate);
				}
			}

		}

		return evaluates;
	}

	/* 根据map集合获取Evaluae对象 */
	private Evaluate getEvaluate(Map<String, Object> map) {
		Evaluate evaluate = null;

		long id = Long.parseLong(map.get("ID").toString());
		String images = (String) map.get("IMAGES");
		String eva_date = (String) map.get("EVA_DATE");
		int state = Integer.parseInt(map.get("STATE").toString());
		String content = (String) map.get("CONTENT");
		long user_id = Long.parseLong(map.get("USER_ID").toString());
		long book_id = Long.parseLong(map.get("PRODUCT_ID").toString());
		int product_version = Integer.parseInt(map.get("PRODUCT_VERSION")
				.toString());

		// 根据user_id 查询用户
		User user = new UserDaoImpl().findUserById(user_id);
		// 根据book_id 查询图书
		Product book = new BookDaoImpl().findBookById(book_id);

		evaluate = new Evaluate(id, images, eva_date, user, book, state,
				product_version, content);

		return evaluate;
	}

}
