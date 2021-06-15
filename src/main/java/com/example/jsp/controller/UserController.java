package com.example.jsp.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.model.Transporter;
import com.example.jsp.commons.oldexception.login.ErrorPassWordExceptionOld;
import com.example.jsp.commons.oldexception.login.UsernameNotExistExceptionOld;
import com.example.jsp.pojo.OrderInfo;
import com.example.jsp.pojo.User;
import com.example.jsp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

	@Autowired
	public void setUserService (UserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setDeliverService(DeliverService deliverService){this.deliverService = deliverService;}
	@Autowired
	public void setGuestService(GuestService guestService){this.guestService = guestService;}
	@Autowired
	public void setOrderService(OrderService orderService){this.orderService = orderService;}
	@Autowired
	public void setStoreService(StoreService storeService){this.storeService = storeService;}

	@Autowired
	public void setOrderService (OrderService orderService) {
		this.orderService = orderService;
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
	 * 列表显示
	 */
	@SaCheckLogin
	@GetMapping("/show/{orderId}")
	public Transporter show(@PathVariable("orderId") Integer orderId) throws ProjectException{
		var transporter = new Transporter();
		var order = orderService.select(orderId);
		var deliver = order.getDeliver();
		var store = order.getStore();
		var guest = order.getGuest();
		var address = order.getAddress();
		var productPackages = order.getProductPackages();
		transporter.addData("order",order)
				.addData("deliver",deliver)
				.addData("store",store)
				.addData("guest",guest)
				.addData("address",address)
				.addData("products",productPackages)
				.setMsg("查询成功");
		return transporter;
	}


	/**
	 *编辑功能（限管理员）
	 */
	@SaCheckRole("admin")
	@GetMapping("/edit/{orderId}/{deliverId}/{storeId}/{guestId}/{addressId}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter restore (@PathVariable("orderId") Integer orderId,
								@PathVariable("deliverId") Integer deliverId,
								@PathVariable("storeId") Integer storeId,
								@PathVariable("guestId") Integer guestId,
								@PathVariable("addressId") Integer addressId) throws ProjectException{
		var transporter= new Transporter();
		var order = orderService.select(orderId);
		var deliver = deliverService.select(deliverId);
		var store = storeService.select(storeId);
		var guest = guestService.select(guestId);

		order.setDeliver(deliver)
				.setStore(store)
				.setGuest(guest);

		return transporter;
	}

	/**
	 * 删除功能（限管理员）
	 */
	@SaCheckRole("admin")
	@GetMapping("/delete/{orderId}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter delete (@PathVariable("orderId") String orderIdString) throws ProjectException {
		var orderId = Integer.parseInt(orderIdString);
		orderService.delete(orderId);
		return new Transporter().setMsg("删除成功");
	}

	/**
	 * 回退功能（限管理员）
	 */
	@SaCheckRole("admin")
	@GetMapping("/rollback/{orderId}/{status}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter rollback (@PathVariable("orderId") String orderIdString,
	                             @PathVariable("status") String statusString) throws ProjectException {
		var status = Integer.parseInt(statusString);
		var orderId = Integer.parseInt(orderIdString);
		var transporter = new Transporter();
		val select = orderService.select(orderId);
		select.setStatus(status);
		transporter.setMsg("回退成功");
		return transporter;
	}
	/**
	 * 订单异常报告
	 */
	@SaCheckRole("admin")
	@GetMapping("/expection/{orderId}/{msg}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter setMsg(@PathVariable("orderId") Integer orderId,
							  @PathVariable("msg") String msg) throws ProjectException{
		var transporter = new Transporter();
		var order = orderService.select(orderId);
		var orderInfo = new OrderInfo();
		orderInfo.setMessage(msg);
		orderService.addException(order,orderInfo);
		return transporter.setMsg("异常报告成功");
	}


}
