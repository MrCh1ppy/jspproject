package com.example.jsp.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.model.Transporter;
import com.example.jsp.commons.oldexception.login.ErrorPassWordExceptionOld;
import com.example.jsp.commons.oldexception.login.UsernameNotExistExceptionOld;
import com.example.jsp.pojo.User;
import com.example.jsp.service.OrderService;
import com.example.jsp.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

/**
 * @author 橙鼠鼠
 */
@RestController
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	private OrderService orderService;

	@Autowired
	public void setUserService (UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login/{type}/{username}/{password}")
	public Transporter login (@PathVariable("username") String username,
	                          @PathVariable("password") String password,
	                          @PathVariable("type") String type) throws UsernameNotExistExceptionOld, ErrorPassWordExceptionOld, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		var transporter = new Transporter();
		var user = new User().setPassword(password).setUsername(username);
		String login = userService.login(user, type);
		transporter.setCode(0)
				.addData("id", login)
				.addData("tokenValue", StpUtil.getTokenValue())
				.addData("tokenName", StpUtil.getTokenName())
				.setMsg("登录成功");
		return transporter;
	}

	@GetMapping("/enroll/{username}/{password}")
	public Transporter enroll (@PathVariable("username") String username,
							   @PathVariable("password") String password) throws ProjectException {
		var user = new User().setUsername(username).setPassword(password);
		userService.create(user);
		return new Transporter().setMsg("管理员注册成功");
	}

	/**
	 *编辑功能（限管理员）
	 */
	@SaCheckRole("admin")
	@GetMapping("/edit/{orderId}/{deliverId}/{storeId}/{guestId}/{address}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter restore (@PathVariable("orderId") Integer orderId,
								@PathVariable("deliverId") Integer deliverId,
								@PathVariable("storeId") Integer storeId,
								@PathVariable("guestId") Integer guestId,
								@PathVariable("address") Integer adressId) throws ProjectException{
		Transporter transporter= new Transporter();

		return transporter;
	}
	/**
	 *删除功能（限管理员）
	 */
	@SaCheckRole("admin")
	@GetMapping("/delete/{orderId}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter delete (@PathVariable("orderId") Integer orderId) throws ProjectException{
		orderService.delete(orderId);
		return new Transporter().setMsg("删除成功");
	}
	/**
	 *回退功能（限管理员）
	 */
	@SaCheckRole("admin")
	@GetMapping("/rollback/{orderId}/{status}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter rollback (@PathVariable("orderId") Integer orderId,
								 @PathVariable("status") Integer status) throws ProjectException{

		Transporter transporter = new Transporter();
		val select=orderService.select(orderId);
		select.setStatus(status);
		transporter.setMsg("回退成功");
		return transporter;
	}

}
