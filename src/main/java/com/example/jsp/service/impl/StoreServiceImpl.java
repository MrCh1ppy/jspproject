package com.example.jsp.service.impl;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.manager.toservice.ProductManager;
import com.example.jsp.manager.toservice.StoreManager;
import com.example.jsp.pojo.Product;
import com.example.jsp.pojo.Store;
import com.example.jsp.pojo.User;
import com.example.jsp.service.StoreService;
import com.example.jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class StoreServiceImpl implements StoreService {

	private StoreManager storeManager;
	private ProductManager productManager;

	private UserService userService;

	@Autowired
	public void setStoreManager (StoreManager storeManager) {
		this.storeManager = storeManager;
	}

	@Autowired
	public void setUserService (UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setProductManager (ProductManager productManager) {
		this.productManager = productManager;
	}

	@Override
	public void create (Store store) throws ProjectException {
		try {
			storeManager.insert(store);
		} catch (SonElementNotExistExceptionOld sonElementNotExistExceptionOld) {
			throw new ProjectException(sonElementNotExistExceptionOld.toString(), 303);
		}
	}

	@Override
	public void delete (Store store) throws ProjectException {
		storeManager.destroy(store);
	}

	@Override
	public void delete (Integer id) throws ProjectException {
		storeManager.destroy(id);
	}

	@Override
	public void restore (Store store) throws ProjectException {
		try {
			storeManager.restore(store);
		} catch (SonElementNotExistExceptionOld sonElementNotExistExceptionOld) {
			throw new ProjectException(sonElementNotExistExceptionOld.toString(), 303);
		}
	}

	@Override
	public Store select (Integer storeId) throws ProjectException {
		return storeManager.select(storeId);

	}

	@Override
	public List<Store> select () {

		return storeManager.select();
	}

	@Override
	public void enroll (Store store, User user) throws ProjectException {
		userService.create(user);
		create(store);
	}

	@Override
	public StoreService addProduct (Store target, Product product) {
		product.setStore(target);
		try {
			productManager.insert(product);
		} catch (SonElementNotExistExceptionOld sonElementNotExistExceptionOld) {
			sonElementNotExistExceptionOld.printStackTrace();
		}
		return this;
	}


}
