package com.guaishushu.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 获取数据库连接的类
 * 
 * @author 付旺辉
 * 
 */
public class ConnectionFactory {

	private static Connection conn;
	private static Properties properties;
	private static String driver;
	private static String URL;
	private static String name;
	private static String passwd;

	// 注册驱动,只需要加载一次,所以写在静态代码块里面
	static {
		properties = new Properties();
		try {
			properties
					.load(new FileInputStream(
							"F:\\briup_school\\hujavaee\\MyBookShop\\src\\com\\guaishushu\\db\\db.properties"));
			driver = properties.getProperty("driver");
			// System.out.println(driver);
			Class.forName(driver);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		URL = properties.getProperty("url");
		name = properties.getProperty("name");
		passwd = properties.getProperty("password");

		// 获取连接
		try {
			conn = DriverManager.getConnection(URL, name, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;

	}

	public static void main(String[] args) {
		System.out.println(getConnection());
	}

}
