package com.example.jsp.controller;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.model.Transporter;
import com.example.jsp.commons.oldexception.login.ErrorPassWordExceptionOld;
import com.example.jsp.commons.oldexception.login.UsernameNotExistExceptionOld;
import com.example.jsp.utils.execption.ErrorInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 橙鼠鼠
 * @apiNote :全局异常处理
 */
@RestControllerAdvice
@RequestMapping("/project")
public class ProjectController {
	@ExceptionHandler(ProjectException.class)
	Transporter handleEnrollException (ProjectException e) {
		//打印异常栈
		e.printStackTrace();
		//获取错误代码
		Integer errorCode = e.getErrorCode();
		//新建一个trans对象用于传递对象
		var transporter = new Transporter();
		//为运输者提供错误代码,以及对应键值对对应的错误信息.
		transporter.fail(errorCode, ErrorInfo.getInfo().get(errorCode.toString()));
		//返回对象供前端解析
		return transporter;
	}

	@ExceptionHandler(Exception.class)
	Transporter handlerInnerError (Exception e) {
		var tTransporter = new Transporter();
		e.printStackTrace();
		tTransporter.fail(666, "内部错误");
		return tTransporter;
	}

	@ExceptionHandler(ErrorPassWordExceptionOld.class)
	Transporter handleErrorPassWordException (Exception e) {
		e.printStackTrace();
		var transporter = new Transporter();
		transporter.fail(20, "密码错误");
		return transporter;
	}
	//提供此泛型的参数化类型。

	@ExceptionHandler(UsernameNotExistExceptionOld.class)
	Transporter handleUsernameNotExistException (Exception e) {
		e.printStackTrace();
		var transporter = new Transporter();
		transporter.fail(21, "用户名不存在");
		return transporter;
	}

	@ExceptionHandler(NotLoginException.class)
	Transporter handleNotLoginException (NotLoginException e) {
		e.printStackTrace();
		var transporter = new Transporter();
		transporter.fail(5, e.getType());
		return transporter;
	}

	@ExceptionHandler(NotPermissionException.class)
	Transporter handleNotPermissionException (NotPermissionException e) {
		e.printStackTrace();
		var transporter = new Transporter();
		transporter.fail(6, "权限不足");
		return transporter;
	}
}
