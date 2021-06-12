package com.example.jsp.manager.toservice;

import com.example.jsp.commons.oldexception.manager.ElementAlreadyExistExceptionOld;
import com.example.jsp.pojo.User;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface UserManager {

	void destroy (Integer id);

	void destroy (User user);

	Integer restore (User user);

	User select (Integer id);

	List<User> select ();

	Integer insert (User targetUser) throws ElementAlreadyExistExceptionOld;

	Boolean isNotExist (Integer id);

	Integer findByUsername (String username);

	User findUserByUsername (String username);

	Boolean isNotExist (String username);
}
