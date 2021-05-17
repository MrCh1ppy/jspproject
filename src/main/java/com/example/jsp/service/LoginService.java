package com.example.jsp.service;

import com.example.jsp.commons.exception.login.LoginException;
import com.example.jsp.commons.model.Token;
import org.springframework.stereotype.Service;

/**
 * @author 橙鼠鼠
 */
@Service
public interface LoginService {
	void login(Token token)throws LoginException;
}
