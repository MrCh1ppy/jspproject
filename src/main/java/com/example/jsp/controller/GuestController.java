package com.example.jsp.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.model.Transporter;
import com.example.jsp.pojo.*;
import com.example.jsp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 完颜
 */

@RestController
public class GuestController {
	private GuestService guestService;
	private OrderService orderService;
	private StoreService storeService;

	@Autowired
	public void setGuestService (GuestService guestService) {
		this.guestService = guestService;
	}

	@Autowired
	private void setOrderService (OrderService orderService) {
		this.orderService = orderService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	@GetMapping("/enroll/{username}/{password}/{name}/{address}/{telephone}")
	public Transporter enroll (@PathVariable("username") String username,
	                           @PathVariable("password") String password,
	                           @PathVariable("name") String name,
	                           @PathVariable("address") String addresses,
	                           @PathVariable("telephone") String telephone) throws ProjectException {
		final var user = new User()
				.setUsername(username)
				.setPassword(password);
		List<Address> address = guestService.apart(addresses);
		final var guest = new Guest()
				.setName(name)
				.setTelephone(telephone)
				.setAddresses(address)
				.setLoginUser(user);
		guestService.enroll(guest, user);
		return new Transporter().setMsg("注册成功");
	}

	/**
	 * 管理顾客页
	 * 顾客列表显示
	 */
	@SaCheckLogin
	@GetMapping("/show")
	public Transporter show() throws ProjectException{
		var transporter = new Transporter();
		var select = guestService.select();
		return transporter.addData("guest",select).setMsg("查询成功");
	}

	/**
	 * 订单列表页
	 * 顾客确认收货
	 */
	@SaCheckRole(value = {"admin", "guest"}, mode = SaMode.OR)
	@GetMapping("/take/{orderId}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter takeOrder (@PathVariable("orderId") String orderIdString) throws ProjectException {
		var orderId = Integer.parseInt(orderIdString);
		var transporter = new Transporter();
		val select = orderService.select(orderId);
		val status = select.getStatus();
		select.setStatus(status + 1);
		transporter.addData("status", status + 1)
				.setMsg("查询成功");
		return transporter;
	}

	/**
	 * 管理顾客页
	 * 编辑
	 */
	@SaCheckRole(value = {"admin", "guest"}, mode = SaMode.OR)
	@GetMapping("/user/{userid}/{username}/{usertel}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter edit (@PathVariable("userid") String userIdString,
	                         @PathVariable("username") String userName,
	                         @PathVariable("usertel") String userTel) throws ProjectException {
		var userId = Integer.parseInt(userIdString);
		val select = guestService.select(userId);
		select.setName(userName)
				.setTelephone(userTel);
		guestService.restore(select);
		return new Transporter().setMsg("编辑成功");
	}

	/**
	 * 管理顾客页
	 * 删除
	 */
	@SaCheckRole("guest")
	@GetMapping("/delete/{userid}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter delete (@PathVariable("userid") String userIdString) throws ProjectException {
		var userId = Integer.parseInt(userIdString);
		guestService.delete(userId);
		return new Transporter().setMsg("删除成功");
	}

	/**
	 * 顾客信息页
	 * 顾客信息显示
	 */
	@SaCheckRole("guest")
	@GetMapping("/info/{guestId}")
	public Transporter showInfo (@PathVariable("guestId") Integer guestId) throws ProjectException {
		val select = guestService.select(guestId);
		Transporter transporter = new Transporter();
		transporter.addData("guest", select)
				.setMsg("查询成功");
		return transporter;
	}

	/**
	 * 顾客信息页
	 * 顾客信息修改
	 */
	@SaCheckRole("guest")
	@GetMapping("/enroll/{guestId}/{guestName}{guestTelephone}/{guestAddress}/")
	public Transporter edit (@PathVariable("guestId") Integer guestId,
	                         @PathVariable("guestName") String guestName,
	                         @PathVariable("guestTelephone") String guestTelephone,
	                         @PathVariable("guestAddress") String guestAddress) throws ProjectException {
		val select = guestService.select(guestId);
		val address = new Address();
		address.setAddressString(guestAddress);
		select.setName(guestName)
				.setTelephone(guestTelephone)
				.getAddresses().add(address);
		guestService.restore(select);
		return new Transporter().setMsg("修改成功");
	}
	/**
	 *创建订单
	 */
	@SaCheckRole("admin")
	@GetMapping("/create/{storeId}/{productId}/{productNum}/{guestId}/{addressId}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter create (@PathVariable("storeId") Integer storeId,
								@PathVariable("productId") Integer productId,
								@PathVariable("productNum") Integer productNum,
								@PathVariable("guestId") Integer guestId,
								@PathVariable("addressId") Integer addressId) throws ProjectException{
		var transporter= new Transporter();
		var order = new Order();
		var store = storeService.select(storeId);
		var guest = guestService.select(guestId);
		orderService.addProduct(order,productId,productNum);
		order.setStore(store)
				.setGuest(guest)
				.setAddress(guestService.getAddress(addressId));
		return transporter.setMsg("创建成功");
	}



}

