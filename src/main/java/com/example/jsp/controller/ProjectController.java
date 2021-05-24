package com.example.jsp.controller;

import com.example.jsp.commons.model.Transporter;
import com.example.jsp.commons.oldexception.login.ErrorPassWordExceptionOld;
import com.example.jsp.commons.oldexception.login.UsernameNotExistExceptionOld;
import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.utils.execption.ErrorInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 橙鼠鼠
 * @apiNote :全局异常处理
 */
@RestControllerAdvice
public class ProjectController<T> {
	@ExceptionHandler(ProjectException.class)
	Transporter<T> handleEnrollException(ProjectException e){
		//打印异常栈
		e.printStackTrace();
		//获取错误代码
		Integer errorCode = e.getErrorCode();
		//新建一个trans对象用于传递对象
		Transporter<T> transporter = new Transporter<>();
		//为运输者提供错误代码,以及对应键值对对应的错误信息.
		transporter.fail(errorCode, ErrorInfo.getInfo().get(errorCode.toString()));
		//返回对象供前端解析
		return transporter;
	}

	@ExceptionHandler(Exception.class)
	Transporter<T> handlerInnerError(Exception e){
		Transporter<T> tTransporter = new Transporter<>();
		e.printStackTrace();
		tTransporter.fail(1,"内部错误");
		return tTransporter;
	}

	@ExceptionHandler(ErrorPassWordExceptionOld.class)
	Transporter<T> handleErrorPassWordException(Exception e){
		e.printStackTrace();
		Transporter<T> transporter = new Transporter<>();
		transporter.fail(20,"密码错误");
		return transporter;
	}

	@ExceptionHandler(UsernameNotExistExceptionOld.class)
	Transporter<T> handleUsernameNotExistException(Exception e){
		e.printStackTrace();
		Transporter<T> transporter = new Transporter<>();
		transporter.fail(21,"用户名不存在");
		return transporter;
	}
}
