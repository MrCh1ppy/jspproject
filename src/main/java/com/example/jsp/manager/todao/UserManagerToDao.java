package com.example.jsp.manager.todao;

import com.example.jsp.pojo.User;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface UserManagerToDao {
	Integer save (User targetUser);

	void del (Integer id);

	void update (User user);

	User select (Integer id);

	List<User> select ();

	User getId (User target);

	Integer findByUsername (String username);

	User findUserByUsername (String username);
}
