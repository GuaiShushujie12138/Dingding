package com.guaishushu.dao;

import java.util.Map;

import com.guaishushu.bean.Category;
import com.guaishushu.bean.CategoryDetail;

public interface CategoryDao {
	/* 查询所有的图书大分类 */
	Map<Long, Category> findAllCategorys();

	/* 根据所属大分类的id查找小分类 */
	Map<Long, CategoryDetail> findCategoryDetailsByCategoryId(long id);

	/* 根据图书id查找所属的大分类 */
	Category findCategoryByBookId(long bookId);

	/* 根据小分类id查找小分类 */
	public CategoryDetail findDetailByDetailId(long detail_id);

	/* 根据category_id查询大分类 */
	public Category findCategoryByCategoryId(long category_id);
}
