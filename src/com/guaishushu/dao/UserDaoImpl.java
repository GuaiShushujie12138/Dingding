package com.guaishushu.dao;

import java.util.List;
import java.util.Map;

import com.guaishushu.bean.User;
import com.guaishushu.db.SQLHelper;

public class UserDaoImpl implements UserDao {
	private SQLHelper helper;

	@Override
	/* 判断用户是否存在,用于登录 */
	public boolean isExits(User user) {
		boolean flag = false;
		User user2 = findUserByName(user.getName());

		// System.out.println("isExits user : " + user);
		// System.out.println("isExits 查询到的user : " + user2);

		if (user2 != null && user.getName().equals(user2.getName())
				&& user.getPassword().equals(user2.getPassword())) {
			flag = true;

		}

		return flag;
	}

	@Override
	/* 根据用户名查找用户 */
	public User findUserByName(String username) {
		User user = null;
		helper = new SQLHelper();

		String sql = "select * from d_user where name = ?";
		String[] params = { username };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				long id = Integer.parseInt(map.get("ID").toString());
				String name = (String) map.get("NAME");
				String passwd = (String) map.get("PASSWORD");
				String zip = (String) map.get("ZIP");
				String address = (String) map.get("ADDRESS");
				String phone = (String) map.get("PHONE");
				String email = (String) map.get("EMAIL");

				user = new User(id, name, passwd, zip, address, phone, email);
			}
		}

		// System.out.println("UserDaoImpl 查询到的user : " + user);

		return user;
	}

	@Override
	/* 添加用户 */
	public void addUser(User user) {
		helper = new SQLHelper();

		String sql = "insert into d_user values (d_user_seq.nextVal, ?, ?, ?, ?, ?, ?)";
		Object[] params = { user.getName(), user.getPassword(), user.getZip(),
				user.getAddress(), user.getPhone(), user.getEmail() };

		helper.insert(sql, params);
	}

	@Override
	/* 根据用户id更新用户 */
	public void updateUser(Long userId, User user) {
		helper = new SQLHelper();

		String sql = "update d_user set "
				+ "password = ?, zip = ?, address = ?, phone = ?, email = ?"
				+ "where id = ?";

		Object[] params = new Object[] { user.getPassword(), user.getZip(),
				user.getAddress(), user.getPhone(), user.getEmail(), userId };
		helper.update(sql, params);
	}

	@Override
	/* 根据用户id查找用户 */
	public User findUserById(long user_id) {
		helper = new SQLHelper();
		User user = null;

		String sql = "select * from d_user where id = ?";
		Long[] params = { user_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				long id = Integer.parseInt(map.get("ID").toString());
				String name = (String) map.get("NAME");
				String passwd = (String) map.get("PASSWORD");
				String zip = (String) map.get("ZIP");
				String address = (String) map.get("ADDRESS");
				String phone = (String) map.get("PHONE");
				String email = (String) map.get("EMAIL");

				user = new User(id, name, passwd, zip, address, phone, email);
			}
		}

		return user;
	}
}
