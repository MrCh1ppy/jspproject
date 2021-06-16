package com.example.jsp.service;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.pojo.Product;
import com.example.jsp.pojo.Store;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface ProductService {
	void create (Product target) throws ProjectException;

	void delete (Integer productId) throws ProjectException;

	void delete (Product product) throws ProjectException;

	void restore (Product product) throws ProjectException;

	Product select (Integer productId);

	List<Product> select ();

	List<Product> selectByStore (Store store);

	List<Product> selectByStore (Integer storeId);
}
