package com.example.jsp.manager.toservice;

import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.pojo.Product;
import com.example.jsp.pojo.Store;
import com.example.jsp.pojo.User;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface StoreManager {
	Integer insert (Store store) throws SonElementNotExistExceptionOld;

	void destroy (Integer id);

	void destroy (Store store);

	Store select (Integer id);

	List<Store> select ();

	Integer restore (Store store) throws SonElementNotExistExceptionOld;

	Integer getId (Store store);

	Boolean isNotExist (Integer id);

	Boolean isStore (int userId);

	User findUserByUserName (String username);

	List<Product> selectHavingProduct (Store store);

	List<Product> selectHavingProduct (Integer storeId);

	Integer selectByUserId (Integer userId);
}
