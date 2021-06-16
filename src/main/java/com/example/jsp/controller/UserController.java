package com.example.jsp.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.model.Transporter;
import com.example.jsp.commons.oldexception.login.ErrorPassWordExceptionOld;
import com.example.jsp.commons.oldexception.login.UsernameNotExistExceptionOld;
import com.example.jsp.manager.toservice.AddressManager;
import com.example.jsp.pojo.User;
import com.example.jsp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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
	private DeliverService deliverService;
	private GuestService guestService;
	private OrderService orderService;
	private StoreService storeService;
	private AddressManager addressManager;

	@Autowired
	public void setAddressManager (AddressManager addressManager) {
		this.addressManager = addressManager;
	}

	@Autowired
	public void setUserService (UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setDeliverService (DeliverService deliverService) {
		this.deliverService = deliverService;
	}

	@Autowired
	public void setGuestService (GuestService guestService) {
		this.guestService = guestService;
	}

	@Autowired
	public void setOrderService (OrderService orderService) {
		this.orderService = orderService;
	}

	@Autowired
	public void setStoreService (StoreService storeService) {
		this.storeService = storeService;
	}


	@GetMapping("/login/{type}/{username}/{password}")
	public Transporter login (@PathVariable("username") String username,
	                          @PathVariable("password") String password,
	                          @PathVariable("type") String type) throws UsernameNotExistExceptionOld, ErrorPassWordExceptionOld, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		var transporter = new Transporter();
		var user = new User().setPassword(password).setUsername(username);
		String login = userService.login(user, type);
		final var ids = login.split("_");
		transporter.setCode(0)
				.addData("id", ids[0])
				.addData("tokenValue", StpUtil.getTokenValue())
				.addData("tokenName", StpUtil.getTokenName())
				.addData("targetId", ids[1])
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

}
