package com.example.jsp.service;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.oldexception.login.ErrorPassWordExceptionOld;
import com.example.jsp.commons.oldexception.login.UsernameNotExistExceptionOld;
import com.example.jsp.pojo.User;

import java.lang.reflect.InvocationTargetException;

/**
 * @author 橙鼠鼠
 */
public interface UserService {
	String login (User user, String type) throws UsernameNotExistExceptionOld, ErrorPassWordExceptionOld,NoSuchMethodException,IllegalAccessException, InvocationTargetException;
	void create (User user) throws ProjectException;
}
