package com.example.jsp.manager.todao;

import com.example.jsp.pojo.Store;
import com.example.jsp.pojo.User;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface StoreManagerToDao {
	Integer save (Store store);

	void delete (Integer id);

	Store select (Integer id);

	List<Store> select ();

	void update (Store store);

	Integer getId (Store store);

	Boolean isStore (int userId);

	User findUserByUserName (String username);

	Integer selectByUserId (Integer userId);
}
