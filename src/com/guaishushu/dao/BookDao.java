package com.guaishushu.dao;

import java.util.List;
import java.util.Set;

import com.guaishushu.bean.Payway;
import com.guaishushu.bean.PriceRank;
import com.guaishushu.bean.Product;
import com.guaishushu.bean.Publish;
import com.guaishushu.bean.Report;

public interface BookDao {
	/* 查询图书 ,把大分类小分类以及出版社和价格区间的条件全放在一起 */
	Set<Product> findAllBooks(String type, long type_id, long rank_id,
			long publish_id);

	/* 查询大分类下的所有图书 */
	Set<Product> findBooksByCategoryId(long category_id);

	/* 查询大分类下并且出版社id为xx的所有图书 */
	Set<Product> findBooksByCategoryIdAndPublish(long category_id,
			long publish_id);

	/* 查询大分类下并且图书价格在rankid区间的所有图书 */
	Set<Product> findBooksByCategoryIdAndRank(long category_id, long rank_id);

	/* 查询大分类下并且出版社id为xx并且图书价格在rankid区间的所有图书 */
	Set<Product> findBooksByCategoryIdAndPublishAndRank(long category_id,
			long publish_id, long rank_id);

	/* 查询某个小分类下的所有图书 */
	Set<Product> findBooksByDetailId(long detail_id);

	/* 查询小分类下并且出版社id为xx的所有图书 */
	Set<Product> findBooksByDetailIdAndPublish(long detail_id, long publish_id);

	/* 查询小分类下并且图书价格在rankid区间的所有图书 */
	Set<Product> findBooksByDetailIdAndRank(long detail_id, long rank_id);

	/* 查询小分类下并且出版社id为xx并且图书价格在rankid区间的所有图书 */
	Set<Product> findBooksByDetailIdAndPublishAndRank(long detail_id,
			long publish_id, long rank_id);

	/* 根据出版社id查询出版社 */
	Publish findPublishById(long publish_id);

	/* 查询热卖的图书 */
	Set<Product> findHotBooks(long flag);

	/* 根据图书id查找图书 */
	Product findBookById(long bookId);

	/* 根据版本号查询图书价格利率 */
	double getRate(int version);

	/* 获取图书的所有支付方式 */
	Set<Payway> getAllPayways();

	/* 根据图书id查询库存 */
	int findStockByBookId(long bookId);

	/* 根据bookId和库存更新库存和销量 */
	void updateBookStock(long bookId, long stock, long sale_num);

	/* 根据payway_id 查找Payway */
	Payway findPaywayById(long payway_id);

	/* 查询所有的出版社 */
	Set<Publish> findAllPublish();

	/* 查询所有的图书价格区间 */
	Set<PriceRank> findallPriceRanks();

	/* 根据rank_id查询PriceRank */
	PriceRank findRankById(long rank_id);

	/* 根据书名查询图书id */
	long findIdByBookName(String bookname);

	/* 根据字符串模糊查询图书名 */
	List<String> findBookNameByString(String str);

	/* 查询出所有的简报 */
	Set<Report> findAllReport();
}
