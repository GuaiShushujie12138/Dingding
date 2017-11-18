package com.guaishushu.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 关闭一些操作数据库的类的类
 * 
 * @author 付旺辉
 * 
 */
public class CloseUtil {

	public static void close(Connection conn, PreparedStatement statement,
			ResultSet resultSet) {

		try {

			if (conn != null) {
				conn.close();
				conn = null;
			}

			if (statement != null) {
				statement.close();
				statement = null;
			}

			if (resultSet != null) {
				resultSet.close();
				resultSet = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(Connection conn, PreparedStatement statement) {
		close(conn, statement, null);
	}

	public static void close(Connection conn, ResultSet resultSet) {
		close(conn, null, resultSet);
	}

}
