package com.guaishushu.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.guaishushu.bean.CategoryDetail;
import com.guaishushu.bean.Payway;
import com.guaishushu.bean.PriceRank;
import com.guaishushu.bean.Product;
import com.guaishushu.bean.Publish;
import com.guaishushu.bean.Report;
import com.guaishushu.db.SQLHelper;

public class BookDaoImpl implements BookDao {
	private SQLHelper helper;

	// public void show() {
	// String sql = "select * from 订单,和用户  where u.id = ${id}";
	// if ("价格" != null) {
	// sql = sql + "and 价格 between  and ";
	// }
	// if ("出版社" != null) {
	// sql = sql + "and 出版社  ";
	// }
	// String sql2 = "";
	//
	// }

	@Override
	/* 查询某个小分类下的所有图书 */
	public Set<Product> findBooksByDetailId(long detail_id) {
		helper = new SQLHelper();
		Set<Product> books = new TreeSet<Product>();

		String sql = "select * from d_product where cat_detail_id = ?";
		Long[] params = { detail_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;
		Product book = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				book = getBook(map);

				books.add(book);
			}
		}
		return books;
	}

	@Override
	/* 根据出版社id查询出版社 */
	public Publish findPublishById(long publish_id) {
		helper = new SQLHelper();
		Publish publish = null;

		String sql = "select * from d_publish where id = ?";
		Long[] params = { publish_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				long id = Long.parseLong(map.get("ID").toString());
				String name = (String) map.get("NAME");

				publish = new Publish(id, name);
			}
		}

		return publish;
	}

	@Override
	/* 查询所有的热卖图书 */
	public Set<Product> findHotBooks(long flag) {
		Set<Product> books = new TreeSet<Product>();
		helper = new SQLHelper();

		String sql = "select * from d_product where flag = ?";
		Long[] params = { flag };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;
		Product book = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				book = getBook(map);

				books.add(book);
			}
		}

		return books;
	}

	@Override
	/* 根据id查找书籍 */
	public Product findBookById(long bookId) {
		Product book = null;
		helper = new SQLHelper();

		String sql = "select * from d_product where id = ?";
		Long[] params = { bookId };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				book = getBook(map);
			}
		}

		return book;
	}

	@Override
	/* 查询大分类下的所有图书 */
	public Set<Product> findBooksByCategoryId(long category_id) {
		Set<Product> books = new TreeSet<Product>();
		helper = new SQLHelper();

		String sql = "select * from d_product where cat_detail_id in (select id from d_category_detail where category_id = ?)";
		Long[] params = { category_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;
		Product book = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				book = getBook(map);
				books.add(book);
			}
		}

		return books;
	}

	@Override
	/* 根据版本号查询图书价格利率 */
	public double getRate(int version) {
		helper = new SQLHelper();
		double rate = 1.0;

		String sql = "select rate from d_book_version where book_version = ?";
		Integer[] params = { version };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				rate = Double.parseDouble(map.get("RATE").toString());
			}
		}

		return rate;
	}

	// 把查询图书的信息封装成一个方法
	private Product getBook(Map<String, Object> map) {

		Product book = null;

		long id = Long.parseLong(map.get("ID").toString());
		String name = (String) map.get("NAME");
		double price = Double.parseDouble(map.get("PRICE").toString());
		String service_fg = (String) map.get("SERVICE_FG");
		String service_myf = (String) map.get("SERVICE_MYF");
		String service_zt = (String) map.get("SERVICE_ZT");
		String service_th = (String) map.get("SERVICE_TH");
		int version = Integer.parseInt(map.get("VERSION").toString());
		long stock = Long.parseLong(map.get("STOCK").toString());
		String description = (String) map.get("DESCRIPTION");
		String writer = (String) map.get("WRITER");
		// 出版社id
		long publish_id = Long.parseLong(map.get("PUBLISH_ID").toString());
		long pages = Long.parseLong(map.get("PAGES").toString());
		String ISBN = (String) map.get("ISBN");
		String publish_date = (String) map.get("PUBLISH_DATE");
		String feature_images = (String) map.get("FEATURE_IMAGES");
		String images = (String) map.get("IMAGES");
		String bill = (String) map.get("BILL");
		// book所属小分类的id
		long CAT_DETAIL_ID = Long
				.parseLong(map.get("CAT_DETAIL_ID").toString());
		String start_date = (String) map.get("START_DATE");
		long sale_num = Long.parseLong(map.get("SALE_NUM").toString());
		int flag = Integer.parseInt(map.get("FLAG").toString());
		String explain = (String) map.get("EXPLAIN");

		// 查询出版社
		Publish publish = findPublishById(publish_id);

		// 查询图书的所属小分类
		CategoryDao dao = new CategoryDaoImpl();
		CategoryDetail detail = dao.findDetailByDetailId(CAT_DETAIL_ID);

		book = new Product(id, name, price, service_fg, service_myf,
				service_zt, service_th, version, stock, description, writer,
				pages, ISBN, publish_date, feature_images, images, bill,
				start_date, sale_num, flag, explain, publish);
		book.setCate_detail(detail);

		return book;
	}

	@Override
	/* 获取所有的支付方式 */
	public Set<Payway> getAllPayways() {
		Set<Payway> payways = new TreeSet<Payway>();
		helper = new SQLHelper();

		String sql = "select * from d_payway where id > 0";
		List<Map<String, Object>> list = helper.query(sql, null);
		Map<String, Object> map = null;
		Payway payway = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				long id = Long.parseLong(map.get("ID").toString());
				String pay_style = (String) map.get("PAY_STYLE");
				String images = (String) map.get("IMAGES");

				payway = new Payway(id, pay_style, images);
				payways.add(payway);
			}
		}

		return payways;
	}

	@Override
	/* 根据图书id查询库存 */
	public int findStockByBookId(long bookId) {
		int stock = 0;
		helper = new SQLHelper();

		String sql = "select stock from d_product where id = ?";
		Long[] params = { bookId };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				stock = Integer.parseInt(map.get("STOCK").toString());
			}
		}

		return stock;
	}

	@Override
	/* 更新图书库存和销量 */
	public void updateBookStock(long bookId, long stock, long sale_num) {
		helper = new SQLHelper();

		String sql = "update d_product set stock = ?, sale_num = ? where id = ?";
		Object[] params = new Object[] { stock, sale_num, bookId };
		helper.update(sql, params);
	}

	@Override
	/* 根据payway_id 查找Payway */
	public Payway findPaywayById(long payway_id) {
		helper = new SQLHelper();
		Payway payway = null;

		String sql = "select * from d_payway where id = ?";
		Long[] params = { payway_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				String pay_style = (String) map.get("PAY_STYLE");
				String images = (String) map.get("IMAGES");

				payway = new Payway(payway_id, pay_style, images);
			}
		}

		return payway;
	}

	@Override
	/* 查询所有的出版社 */
	public Set<Publish> findAllPublish() {
		helper = new SQLHelper();
		Set<Publish> publishs = new TreeSet<Publish>();

		String sql = "select * from d_publish";
		List<Map<String, Object>> list = helper.query(sql, null);
		Map<String, Object> map = null;
		Publish publish = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				long id = Long.parseLong(map.get("ID").toString());
				String name = (String) map.get("NAME");

				publish = new Publish(id, name);
				publishs.add(publish);
			}
		}

		return publishs;
	}

	@Override
	/* 查询所有的图书价格区间 */
	public Set<PriceRank> findallPriceRanks() {
		helper = new SQLHelper();
		Set<PriceRank> ranks = new TreeSet<PriceRank>();

		String sql = "select * from d_price_rank";
		List<Map<String, Object>> list = helper.query(sql, null);
		Map<String, Object> map = null;
		PriceRank rank = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				long id = Long.parseLong(map.get("ID").toString());
				long min_price = Long
						.parseLong(map.get("MIN_PRICE").toString());
				long max_price = Long
						.parseLong(map.get("MAX_PRICE").toString());

				rank = new PriceRank(id, min_price, max_price);
				ranks.add(rank);
			}
		}

		return ranks;
	}

	@Override
	/* 查询大分类下并且出版社id为xx的所有图书 */
	public Set<Product> findBooksByCategoryIdAndPublish(long category_id,
			long publish_id) {
		helper = new SQLHelper();
		Set<Product> books = new TreeSet<Product>();

		String sql = "select * from d_product where cat_detail_id in (select id from d_category_detail where category_id = ?) and publish_id = ?";
		Long[] params = { category_id, publish_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;
		Product book = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				book = getBook(map);
				books.add(book);
			}
		}

		return books;
	}

	@Override
	/* 查询大分类下并且图书价格在rankid区间的所有图书 */
	public Set<Product> findBooksByCategoryIdAndRank(long category_id,
			long rank_id) {
		helper = new SQLHelper();
		Set<Product> books = new TreeSet<Product>();

		String sql = "select * from d_product where cat_detail_id in (select id from d_category_detail where category_id = ?) and price between ? and ?";
		// 根据rank_id查找PriceRank
		PriceRank rank = findRankById(rank_id);

		Long[] params = { category_id, rank.getMin_price(), rank.getMax_price() };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;
		Product book = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				book = getBook(map);
				books.add(book);
			}
		}

		return books;
	}

	@Override
	/* 查询大分类下并且出版社id为xx并且图书价格在rankid区间的所有图书 */
	public Set<Product> findBooksByCategoryIdAndPublishAndRank(
			long category_id, long publish_id, long rank_id) {
		helper = new SQLHelper();
		Set<Product> books = new TreeSet<Product>();

		String sql = "select * from d_product where cat_detail_id in (select id from d_category_detail where category_id = ?) and publish_id = ? and price between ? and ?";
		// 根据rank_id查找PriceRank
		PriceRank rank = findRankById(rank_id);

		Long[] params = { category_id, publish_id, rank.getMin_price(),
				rank.getMax_price() };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;
		Product book = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				book = getBook(map);
				books.add(book);
			}
		}

		return books;
	}

	@Override
	/* 根据rank_id查询PriceRank */
	public PriceRank findRankById(long rank_id) {
		helper = new SQLHelper();
		PriceRank rank = null;

		String sql = "select * from d_price_rank where id = ?";
		Long[] params = { rank_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				long min_price = Long
						.parseLong(map.get("MIN_PRICE").toString());
				long max_price = Long
						.parseLong(map.get("MAX_PRICE").toString());

				rank = new PriceRank(rank_id, min_price, max_price);
			}
		}

		return rank;
	}

	@Override
	/* 查询小分类下并且出版社id为xx的所有图书 */
	public Set<Product> findBooksByDetailIdAndPublish(long detail_id,
			long publish_id) {
		helper = new SQLHelper();
		Set<Product> books = new TreeSet<Product>();

		String sql = "select * from d_product where cat_detail_id = ? and publish_id = ?";
		Long[] params = { detail_id, publish_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;
		Product book = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				book = getBook(map);

				books.add(book);
			}
		}
		return books;
	}

	@Override
	/* 查询小分类下并且图书价格在rankid区间的所有图书 */
	public Set<Product> findBooksByDetailIdAndRank(long detail_id, long rank_id) {
		helper = new SQLHelper();
		Set<Product> books = new TreeSet<Product>();

		String sql = "select * from d_product where cat_detail_id = ? and price between ? and ?";

		PriceRank rank = findRankById(rank_id);

		Long[] params = { detail_id, rank.getMin_price(), rank.getMax_price() };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;
		Product book = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				book = getBook(map);

				books.add(book);
			}
		}
		return books;
	}

	@Override
	/* 查询小分类下并且出版社id为xx并且图书价格在rankid区间的所有图书 */
	public Set<Product> findBooksByDetailIdAndPublishAndRank(long detail_id,
			long publish_id, long rank_id) {
		helper = new SQLHelper();
		Set<Product> books = new TreeSet<Product>();

		String sql = "select * from d_product where cat_detail_id = ? and publish_id = ? and price between ? and ?";

		PriceRank rank = findRankById(rank_id);

		Long[] params = { detail_id, publish_id, rank.getMin_price(),
				rank.getMax_price() };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;
		Product book = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				book = getBook(map);

				books.add(book);
			}
		}
		return books;
	}

	@Override
	/* 查询图书,管他是大分类还是小分类,出版社是什么,价格区间在哪,就是一梭子 */
	public Set<Product> findAllBooks(String type, long type_id, long rank_id,
			long publish_id) {
		helper = new SQLHelper();
		Set<Product> books = new TreeSet<Product>();
		String sql = "";
		Object[] params = null;

		// 判断当前查询的类型,是大分类还是小分类
		if ("category".equals(type)) {
			// 大分类
			sql = "select * from d_product where cat_detail_id in (select id from d_category_detail where category_id = ?)";
			params = new Object[] { type_id };

			if (publish_id != -1 && rank_id == -1) {
				sql = "select * from d_product where cat_detail_id in (select id from d_category_detail where category_id = ?) and publish_id = ?";
				params = new Object[] { type_id, publish_id };

			} else if (publish_id == -1 && rank_id != -1) {
				PriceRank rank = findRankById(rank_id);
				sql = "select * from d_product where cat_detail_id in (select id from d_category_detail where category_id = ?) and price between ? and ?";
				params = new Object[] { type_id, rank.getMin_price(),
						rank.getMax_price() };

			} else if (publish_id != -1 && rank_id != -1) {
				PriceRank rank = findRankById(rank_id);
				sql = "select * from d_product where cat_detail_id in (select id from d_category_detail where category_id = ?) and publish_id = ? and price between ? and ?";
				params = new Object[] { type_id, publish_id,
						rank.getMin_price(), rank.getMax_price() };

			}

		} else if ("detail".equals(type)) {
			// 小分类
			sql = "select * from d_product where cat_detail_id = ?";
			params = new Object[] { type_id };

			if (publish_id != -1 && rank_id == -1) {
				sql = "select * from d_product where cat_detail_id = ? and publish_id = ?";
				params = new Object[] { type_id, publish_id };

			} else if (publish_id == -1 && rank_id != -1) {
				PriceRank rank = findRankById(rank_id);
				sql = "select * from d_product where cat_detail_id = ? and price between ? and ?";
				params = new Object[] { type_id, rank.getMin_price(),
						rank.getMax_price() };

			} else if (publish_id != -1 && rank_id != -1) {
				PriceRank rank = findRankById(rank_id);
				sql = "select * from d_product where cat_detail_id = ? and publish_id = ? and price between ? and ?";
				params = new Object[] { type_id, publish_id,
						rank.getMin_price(), rank.getMax_price() };

			}

		}

		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;
		Product book = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				book = getBook(map);

				books.add(book);
			}
		}

		return books;
	}

	@Override
	/* 根据书名查询图书id */
	public long findIdByBookName(String bookname) {
		helper = new SQLHelper();
		long book_id = -1;

		String sql = "select id from d_product where name = ?";
		String[] params = { bookname };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				book_id = Long.parseLong(map.get("ID").toString());
			}
		}

		return book_id;
	}

	@Override
	/* 根据字符串模糊查询书名 */
	public List<String> findBookNameByString(String str) {
		List<String> bookNames = new ArrayList<String>();
		helper = new SQLHelper();

		String sql = "select name from d_product where name like ?";
		String[] params = { "%" + str + "%" };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				String bookName = (String) map.get("NAME");
				bookNames.add(bookName);
			}
		}

		return bookNames;
	}

	@Override
	/* 查询出所有简报 */
	public Set<Report> findAllReport() {
		Set<Report> reports = new TreeSet<Report>();
		helper = new SQLHelper();

		String sql = "select * from d_report";
		List<Map<String, Object>> list = helper.query(sql, null);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				long id = Long.parseLong(map.get("ID").toString());
				String title = (String) map.get("TITLE");
				long product_id = Long.parseLong(map.get("PRODUCT_ID")
						.toString());

				// 根据图书id查询图书
				Product book = findBookById(product_id);
				Report report = new Report(id, title, book);

				reports.add(report);
			}
		}

		return reports;
	}
}
