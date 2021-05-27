package com.example.jsp.service;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.pojo.Store;
import com.example.jsp.pojo.User;

/**
 * @author 橙鼠鼠
 */
public interface StoreService {
	void create(Store store)throws ProjectException;
	void enroll(Store store, User user)throws ProjectException;
}
