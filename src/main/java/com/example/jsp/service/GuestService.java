package com.example.jsp.service;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.pojo.Guest;
import com.example.jsp.pojo.User;

/**
 * @author 橙鼠鼠
 */
public interface GuestService {
	void create(Guest target)throws ProjectException;
	void enroll(Guest guest, User user)throws ProjectException;
}
