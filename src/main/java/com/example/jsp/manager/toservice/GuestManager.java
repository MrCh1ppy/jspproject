package com.example.jsp.manager.toservice;

import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.pojo.Guest;
import com.example.jsp.pojo.User;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface GuestManager {
	Integer insert (Guest target) throws SonElementNotExistExceptionOld;

	void destroy (Integer id);

	void destroy (Guest guest);

	Guest select (Integer id);

	List<Guest> select ();

	Integer restore (Guest target) throws SonElementNotExistExceptionOld;

	Integer getId (Guest target);

	Boolean isNotExist (Integer id);

	Boolean isGuest (int userId);

	User findUserByUserName (String username);

	Integer selectByUserId (Integer userId);
}
