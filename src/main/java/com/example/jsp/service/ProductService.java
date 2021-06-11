package com.example.jsp.service;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.pojo.Guest;
import com.example.jsp.pojo.Product;

/**
 * @author 橙鼠鼠
 */
public interface ProductService {
	void create(Product target)throws ProjectException;

	void delete(Integer productId)throws ProjectException;
	void delete(Product product)throws ProjectException;

	void update(Product old, Product latest)throws ProjectException;

	Product select(Integer productId)throws  ProjectException;
}
