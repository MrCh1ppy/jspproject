package com.example.jsp.service;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.pojo.Product;

/**
 * @author 橙鼠鼠
 */
public interface ProductService {
	void create(Product target)throws ProjectException;
	void delete(int productId)throws ProjectException;
	void delete(Product product)throws ProjectException;
}
