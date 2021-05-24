package com.example.jsp.service;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.pojo.Guest;

/**
 * @author 橙鼠鼠
 */
public interface GuestService {
	void create(Guest target)throws ProjectException;
}
