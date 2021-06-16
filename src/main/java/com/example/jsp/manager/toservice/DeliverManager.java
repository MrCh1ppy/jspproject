package com.example.jsp.manager.toservice;

import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.pojo.Deliver;
import com.example.jsp.pojo.User;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface DeliverManager {
	Integer insert (Deliver deliver) throws SonElementNotExistExceptionOld;

	List<Deliver> select ();

	Deliver select (Integer id);

	Integer getId (Deliver deliver);

	Integer restore (Deliver deliver) throws SonElementNotExistExceptionOld;

	void destroy (Integer id);

	void destroy (Deliver deliver);

	Boolean isNotExist (Integer id);

	Boolean isDeliver (int userId);

	User findUserByUserName (String username);

	Integer selectByUserId (Integer userId);
}
