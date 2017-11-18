package com.guaishushu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.guaishushu.bean.Category;
import com.guaishushu.bean.CategoryDetail;
import com.guaishushu.db.SQLHelper;

public class CategoryDaoImpl implements CategoryDao {
	private SQLHelper helper;

	@Override
	/* 查询所有的图书大分类 */
	public Map<Long, Category> findAllCategorys() {
		Map<Long, Category> categorys = new HashMap<Long, Category>();
		helper = new SQLHelper();

		String sql = "select * from d_category";
		List<Map<String, Object>> list = helper.query(sql, null);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				long id = Long.parseLong(map.get("ID").toString());
				String name = (String) map.get("NAME");
				Category category = new Category(id, name);

				// 根据大分类id查找小分类
				Map<Long, CategoryDetail> details = findCategoryDetailsByCategoryId(id);
				category.setCate_detail(details);

				categorys.put(id, category);
			}
		}

		return categorys;
	}

	@Override
	/* 根据所属大分类的id查找小分类 */
	public Map<Long, CategoryDetail> findCategoryDetailsByCategoryId(
			long category_id) {
		helper = new SQLHelper();
		Map<Long, CategoryDetail> details = new HashMap<Long, CategoryDetail>();

		String sql = "select * from d_category_detail where category_id = ?";
		Long[] params = { category_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;
		CategoryDetail detail = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				long id = Long.parseLong(map.get("ID").toString());
				String name = (String) map.get("NAME");
				detail = new CategoryDetail(id, name);

				// // 根据小分类id查找出所有的图书
				// BookDao bookDao = new BookDaoImpl();
				// Set<Product> books = bookDao.findBooksByDetailId(id);
				// detail.setProducts(books);

				details.put(id, detail);
			}
		}

		return details;
	}

	@Override
	/* 根据图书id查找所属的大分类 */
	public Category findCategoryByBookId(long bookId) {
		helper = new SQLHelper();
		Category category = null;

		String sql = "select * from d_category where id = (select category_id from d_category_detail where id = (select cat_detail_id from d_product where id = ?))";
		Long[] params = { bookId };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				long id = Long.parseLong(map.get("ID").toString());
				String name = (String) map.get("NAME");
				category = new Category(id, name);
			}
		}

		return category;
	}

	/* 根据小分类id查找小分类 */
	public CategoryDetail findDetailByDetailId(long detail_id) {
		CategoryDetail detail = null;
		helper = new SQLHelper();

		String sql = "select * from d_category_detail where id = ?";
		Long[] params = { detail_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				long id = Long.parseLong(map.get("ID").toString());
				String name = (String) map.get("NAME");
				long category_id = Long.parseLong(map.get("CATEGORY_ID")
						.toString());
				detail = new CategoryDetail(id, name);

				// 根据category_id查询大分类
				Category category = findCategoryByCategoryId(category_id);
				detail.setCategory(category);
			}
		}

		return detail;
	}

	/* 根据category_id查询大分类 */
	public Category findCategoryByCategoryId(long category_id) {
		Category category = null;
		helper = new SQLHelper();

		String sql = "select * from d_category where id = ?";
		Long[] params = { category_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				long id = Long.parseLong(map.get("ID").toString());
				String name = (String) map.get("NAME");
				category = new Category(id, name);
			}
		}

		return category;
	}

	/*
	 * @Override
	 * 
	 * 利用多表查询,查询所有的大分类名字以及每个大分类下的小分类种类 存放在map集合中,
	 * 
	 * 以大分类为key值,以每个大分类中的小分类的集合为value
	 */
	/*
	 * public Map<Category, List<CategoryDetail>> findAllCategorys() {
	 * Map<Category, List<CategoryDetail>> cates = null; helper = new
	 * SQLHelper(); String sql =
	 * "select a.id aid, a.name aname, b.id bid, b.name bname" +
	 * "from d_category a, d_category_detail b" + "where a.id = b.category_id";
	 * 
	 * List<Map<String, Object>> list = helper.query(sql, null); Map<String,
	 * Object> map = null; Category category = null; CategoryDetail detail =
	 * null; List<CategoryDetail> details = null;
	 * 
	 * if (list != null && list.size() > 0) { for (int i = 0; i < list.size();
	 * i++) { map = list.get(i);
	 * 
	 * 大分类对象 long aid = Long.parseLong(map.get("AID").toString()); String aname
	 * = (String) map.get("ANAME"); category = new Category(aid, aname);
	 * 
	 * 小分类对象 long bid = Long.parseLong(map.get("BID").toString()); String bname
	 * = (String) map.get("BNAME"); detail = new CategoryDetail(bid, bname);
	 * 
	 * // 如果cates中已经存在大分类key值,那么就取出value添加 if (cates.containsKey(category)) {
	 * details = cates.get(category); // 添加 details.add(detail); } else { //
	 * 第一次添加该key值 details = new ArrayList<CategoryDetail>(); } } }
	 * 
	 * return cates; }
	 */
}
