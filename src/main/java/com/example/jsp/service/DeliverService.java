package com.example.jsp.service;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.pojo.Deliver;
import com.example.jsp.pojo.Guest;

/**
 * @author 橙鼠鼠
 */
public interface DeliverService {
	void create(Deliver deliver)throws ProjectException;

	void delete(Integer id)throws ProjectException;
	void delete(Deliver deliver)throws ProjectException;

	void update(Deliver old,Deliver latest) throws ProjectException;

	Deliver select(Integer deliverId)throws ProjectException;

}
