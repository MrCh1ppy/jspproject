package com.example.jsp.service;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.pojo.Deliver;

/**
 * @author 橙鼠鼠
 */
public interface DeliverService {
	void create(Deliver deliver)throws ProjectException;
}
