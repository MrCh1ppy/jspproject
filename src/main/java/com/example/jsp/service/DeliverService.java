package com.example.jsp.service;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.pojo.Deliver;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface DeliverService {
	void create (Deliver deliver) throws ProjectException;

	void delete (Integer id) throws ProjectException;

	void delete (Deliver deliver) throws ProjectException;

	void restore (Deliver deliver) throws ProjectException;

	Deliver select (Integer deliverId) throws ProjectException;

	List<Deliver> select () throws ProjectException;

}
