package com.example.jsp.service.impl;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.manager.toservice.ProductManager;
import com.example.jsp.pojo.Product;
import com.example.jsp.pojo.Store;
import com.example.jsp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class ProductServiceImpl implements ProductService {
	private ProductManager productManager;

	@Autowired
	public void setProductManager (ProductManager productManager) {
		this.productManager = productManager;
	}

	@Override
	public void create (Product target) throws ProjectException {
		try {
			productManager.insert(target);
		} catch (SonElementNotExistExceptionOld sonElementNotExistExceptionOld) {
			throw new ProjectException(sonElementNotExistExceptionOld.toString(), 305);
		}
	}

	@Override
	public void delete (Integer productId) throws ProjectException {
		productManager.destroy(productId);
	}

	@Override
	public void delete (Product product) throws ProjectException {
		productManager.destroy(product);
	}

	@Override
	public void restore (Product product) throws ProjectException {
		try {
			productManager.restore(product);
		} catch (SonElementNotExistExceptionOld e) {
			throw new ProjectException(e.toString(), 302);
		}
	}

	@Override
	public Product select (Integer productId) {
		return productManager.select(productId);
	}

	@Override
	public List<Product> select () {
		return productManager.select();
	}

	@Override
	public List<Product> selectByStore (Store store) {
		return selectByStore(store.getId());
	}

	@Override
	public List<Product> selectByStore (Integer storeId) {
		final var products = new ArrayList<>();
		return productManager.selectByStore(storeId);
	}


}

