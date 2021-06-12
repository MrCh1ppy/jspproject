package com.example.jsp.service.impl;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.manager.toservice.ProductManager;
import com.example.jsp.pojo.Product;
import com.example.jsp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void update (Product latest) throws ProjectException {
		try {
			productManager.restore(latest);
		} catch (SonElementNotExistExceptionOld e) {
			throw new ProjectException(e.toString(), 302);
		}
	}

	@Override
	public Product select (Integer productId) throws ProjectException {
		return productManager.select(productId);
	}
}
