package com.guaishushu.dao;

import com.guaishushu.bean.User;

public interface UserDao {
	/* 判断用户是否可以登录,也就是用户是否存在 */
	boolean isExits(User user);

	/* 根据用户名查找用户 */
	User findUserByName(String username);

	/* 添加用户 */
	void addUser(User user);

	/* 根据用户id更新用户 */
	void updateUser(Long userId, User user);

	/* 根据id查找用户 */
	User findUserById(long user_id);
}
