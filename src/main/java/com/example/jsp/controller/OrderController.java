package com.example.jsp.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.model.Transporter;
import com.example.jsp.manager.toservice.AddressManager;
import com.example.jsp.pojo.Order;
import com.example.jsp.pojo.OrderInfo;
import com.example.jsp.service.DeliverService;
import com.example.jsp.service.GuestService;
import com.example.jsp.service.OrderService;
import com.example.jsp.service.StoreService;
import lombok.val;
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
	private DeliverService deliverService;
	private AddressManager addressManager;

	@Autowired
	public void setGuestService (GuestService guestService) {
		this.guestService = guestService;
	}

	@Autowired
	private void setOrderService (OrderService orderService) {
		this.orderService = orderService;
	}

	@Autowired
	public void setStoreService (StoreService storeService) {
		this.storeService = storeService;
	}

	@Autowired
	public void setDeliverService (DeliverService deliverService) {
		this.deliverService = deliverService;
	}

	@Autowired
	public void setAddressManager (AddressManager addressManager) {
		this.addressManager = addressManager;
	}

	/**
	 * 创建订单
	 */
	@SaCheckRole(value = {"admin", "guest"}, mode = SaMode.OR)
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
				.setAddress(guestService.getAddress(addressId)).setStatus(0).setMessage("创建成功，等待接单");
		orderService.create(order,nums,ids);
		return transporter.setMsg("创建成功");
	}


	@SaCheckRole(value = {"admin", "guest", "deliver", "store"}, mode = SaMode.OR)
	@GetMapping("/show")
	public Transporter selectAll () {
		final List<Order> orderList = orderService.select();
		final var transporter = new Transporter();
		transporter.addData("orderList", orderList).setMsg("success");
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
	 * 订单异常报告
	 */
	@SaCheckRole("admin")
	@GetMapping("/exception/{orderId}/{msg}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter setMsg (@PathVariable("orderId") String orderIdString,
	                           @PathVariable("msg") String msg) throws ProjectException {
		var orderId = Integer.parseInt(orderIdString);
		var transporter = new Transporter();
		var order = orderService.select(orderId);
		var orderInfo = new OrderInfo();
		orderInfo.setMessage(msg);
		orderService.addException(order, orderInfo);
		return transporter.setMsg("异常报告成功");
	}

	/**
	 * 列表显示
	 */
	@SaCheckLogin
	@GetMapping("/show/{orderId}")
	public Transporter show (@PathVariable("orderId") String orderIdString) throws ProjectException {
		var transporter = new Transporter();
		var orderId = Integer.parseInt(orderIdString);
		val order = orderService.select(orderId);
		val deliver = order.getDeliver();
		val store = order.getStore();
		val guest = order.getGuest();
		val address = order.getAddress();
		val productPackages = order.getProductPackages();
		transporter.addData("order", order)
				.addData("deliver", deliver)
				.addData("store", store)
				.addData("guest", guest)
				.addData("address", address)
				.addData("products", productPackages)
				.setMsg("查询成功");
		return transporter;
	}


	/**
	 * 编辑功能（限管理员）
	 */
	@SaCheckRole("admin")
	@GetMapping("/edit/{orderId}/{deliverId}/{storeId}/{guestId}/{addressId}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter restore (@PathVariable("orderId") String orderIdString,
	                            @PathVariable("deliverId") String deliverIdString,
	                            @PathVariable("storeId") String storeIdString,
	                            @PathVariable("guestId") String guestIdString,
	                            @PathVariable("addressId") String addressIdString) throws ProjectException {
		var transporter = new Transporter();
		var orderId = Integer.parseInt(orderIdString);
		var deliverId = Integer.parseInt(deliverIdString);
		var storeId = Integer.parseInt(storeIdString);
		var guestId = Integer.parseInt(guestIdString);
		var addressId = Integer.parseInt(addressIdString);
		val order = orderService.select(orderId);
		val deliver = deliverService.select(deliverId);
		val store = storeService.select(storeId);
		val guest = guestService.select(guestId);
		order.setDeliver(deliver)
				.setStore(store)
				.setGuest(guest).setAddress(addressManager.select(addressId));

		return transporter;
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
}
