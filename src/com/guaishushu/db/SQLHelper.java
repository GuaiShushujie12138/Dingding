package com.guaishushu.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库帮助类
 * 
 * @author 付旺辉
 * 
 */
public class SQLHelper {

	private Connection conn;
	private PreparedStatement statement;
	private ResultSet resultSet;

	// 对数据库表的插入操作
	public boolean insert(String sql, Object[] params) {

		boolean b = true;

		// 获取连接
		conn = ConnectionFactory.getConnection();
		try {
			statement = (PreparedStatement) conn.prepareStatement(sql);

			// 判断参数是否为空
			if (params != null) {
				for (int i = 1; i <= params.length; i++) {
					statement.setObject(i, params[i - 1]);
				}
			}

			// 执行
			b = statement.execute();
			System.out.println("执行 insert 操作成功!");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("执行 insert 操作失败!");
			b = false;
		} finally {
			CloseUtil.close(conn, statement);
		}

		return b;

	}

	// 删除操作
	public boolean delete(String sql, Object[] params) {

		boolean b = true;

		// 获取连接
		conn = ConnectionFactory.getConnection();

		try {
			statement = (PreparedStatement) conn.prepareStatement(sql);

			// 判断参数
			if (params != null) {
				for (int i = 1; i <= params.length; i++) {
					statement.setObject(i, params[i - 1]);
				}
			}

			// 执行
			b = statement.execute();

		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
			System.out.println("执行delete失败!");
		} finally {
			CloseUtil.close(conn, statement);
		}

		return b;

	}

	// 修改操作
	public boolean update(String sql, Object[] params) {

		boolean b = false;

		// 获取连接
		conn = ConnectionFactory.getConnection();
		try {
			statement = (PreparedStatement) conn.prepareStatement(sql);
			for (int i = 1; i <= params.length; i++) {
				statement.setObject(i, params[i - 1]);
			}

			int i = statement.executeUpdate();
			if (i == 1) {
				b = true;
			}
			// System.out.println("SQLHelper flag : " + b);
			System.out.println("执行update成功!");
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
			System.out.println("SQLHelper update failure!");
		} finally {
			CloseUtil.close(conn, statement);
		}

		return b;

	}

	// 查询结果集操作
	private ResultSet getResultSet(String sql, Object[] params) {

		conn = ConnectionFactory.getConnection();

		try {
			statement = conn.prepareStatement(sql);

			// 判断参数
			if (params != null) {
				for (int i = 1; i <= params.length; i++) {
					statement.setObject(i, params[i - 1]);
				}
			}

			resultSet = statement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("执行query失败!");
		}/*
		 * finally { //CloseUtil.close(conn, statement); }
		 */

		return resultSet;

	}

	/**
	 * 查询并获取结果集中的所有行列值
	 */
	public List<Map<String, Object>> query(String sql, Object[] params) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;

		resultSet = getResultSet(sql, params);

		// 获取resultSet中列和行的值
		try {
			// 创建ResultSetMetaData对象
			ResultSetMetaData rsmd = resultSet.getMetaData();
			// 结果集列数
			int column = rsmd.getColumnCount();
			while (resultSet.next()) {
				map = new HashMap<String, Object>();
				for (int i = 1; i <= column; i++) {
					map.put(rsmd.getColumnLabel(i), resultSet.getObject(i));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(conn, statement, resultSet);
		}

		return list;
	}

}
