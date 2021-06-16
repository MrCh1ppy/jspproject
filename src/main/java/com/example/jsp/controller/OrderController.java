package com.example.jsp.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.model.Transporter;
import com.example.jsp.pojo.Order;
import com.example.jsp.service.GuestService;
import com.example.jsp.service.OrderService;
import com.example.jsp.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {
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

	public void setStoreService (StoreService storeService) {
		this.storeService = storeService;
	}

	/**
	 * 创建订单
	 */
	@SaCheckRole("admin")
	@GetMapping("/create/{storeId}/{productId}/{productNum}/{guestId}/{addressId}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter create (@PathVariable("storeId") String storeIdString,
	                           @PathVariable("productId") String productIdString,
	                           @PathVariable("productNum") String productNumString,
	                           @PathVariable("guestId") String guestIdString,
	                           @PathVariable("addressId") String addressIdString) throws ProjectException {
		var storeId = Integer.parseInt(storeIdString);
		var guestId = Integer.parseInt(guestIdString);
		var addressId = Integer.parseInt(addressIdString);
		var transporter = new Transporter();
		var order = new Order();
		var store = storeService.select(storeId);
		var guest = guestService.select(guestId);
		final var ids = productIdString.split(" ");
		final var nums = productNumString.split(" ");


		order.setStore(store)
				.setGuest(guest)
				.setAddress(guestService.getAddress(addressId));
		orderService.create(order,nums,ids);
		return transporter.setMsg("创建成功");
	}

	@SaCheckRole(value = {"admin","guest","deliver","store"},mode = SaMode.OR)
	@GetMapping("/show")
	public Transporter selectAll(){
		final List<Order> orderList = orderService.select();
		final var transporter = new Transporter();
		transporter.addData("orderList",orderList).setMsg("success");
		return transporter;
	}
}
