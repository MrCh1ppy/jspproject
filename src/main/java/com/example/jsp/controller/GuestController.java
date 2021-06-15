package com.example.jsp.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.model.Transporter;
import com.example.jsp.pojo.Address;
import com.example.jsp.pojo.Guest;

import com.example.jsp.pojo.User;
import com.example.jsp.service.GuestService;
import com.example.jsp.service.OrderService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 完颜
 */
@RequestMapping("/guest")
@RestController
public class GuestController {
	private GuestService guestService;
	private OrderService orderService;

	@Autowired
	public void setGuestService (GuestService guestService) {
		this.guestService = guestService;
	}

	@Autowired
	private  void setOrderService(OrderService orderService){
		this.orderService=orderService;
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
	@GetMapping("/showguest/")
	public Transporter showProduct() throws ProjectException{
		Transporter transporter = new Transporter();
		val select = guestService.select();
		return transporter.addData("guest",select).setMsg("查询成功");
	}

	/**
	 * 订单列表页
	 * 顾客确认收货
	 */
	@SaCheckRole("guest")
	@GetMapping("/take/{orderId}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter takeOrder (@PathVariable("orderId") Integer orderId) throws ProjectException{
		Transporter transporter = new Transporter();
		val select = orderService.select(orderId);
		val status =select.getStatus();
		select.setStatus(status+1);
		transporter.addData("status",status+1)
				.setMsg("查询成功");
		return transporter;
	}

	/**
	 * 管理顾客页
	 * 编辑
	 */
	@SaCheckRole("guest")
	@GetMapping("/user/{userid}/{username}/{usertel}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter edit(@PathVariable("userid") Integer userId,
							@PathVariable("username") String userName,
							@PathVariable("usertel") String userTel) throws ProjectException{
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
	@GetMapping("/user/{userid}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter delete(@PathVariable("userid") Integer userId) throws ProjectException{
		guestService.delete(userId);
		return new Transporter().setMsg("删除成功");
	}

}

