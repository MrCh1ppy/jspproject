package com.example.jsp.manager.todao;

import com.example.jsp.pojo.Deliver;
import com.example.jsp.pojo.User;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface DeliverManagerToDao {
	Integer save (Deliver deliver);

	List<Deliver> select ();

	Deliver select (Integer id);

	Integer getId (Deliver deliver);

	void update (Deliver deliver);

	void delete (Integer id);

	Boolean isDeliver (int userId);

	User findUserByUserName (String username);

	Integer selectByUserId (Integer userId);
}
