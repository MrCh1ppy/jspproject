package com.example.jsp.service;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.pojo.Store;

/**
 * @author 橙鼠鼠
 */
public interface StoreService {
	void create(Store store)throws ProjectException;
}
