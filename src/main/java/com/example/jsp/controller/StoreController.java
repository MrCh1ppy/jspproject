package com.example.jsp.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.model.Transporter;
import com.example.jsp.pojo.Store;
import com.example.jsp.pojo.User;
import com.example.jsp.service.OrderService;
import com.example.jsp.service.ProductService;
import com.example.jsp.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


/**
 * @author 橙鼠鼠
 */
@RestController
@RequestMapping("/store")
public class StoreController {
	private StoreService storeService;
	private OrderService orderService;
	private ProductService productService;


	@Autowired
	public void setService (StoreService storeService) {
		this.storeService = storeService;
	}

	@Autowired
	private void setOrderService (OrderService orderService) {
		this.orderService = orderService;
	}

	@Autowired
	private void setProductService (ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/enroll/{username}/{password}/{name}/{address}/{telephone}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter enroll (@PathVariable("username") String username,
	                           @PathVariable("password") String password,
	                           @PathVariable("name") String name,
	                           @PathVariable("address") String address,
	                           @PathVariable("telephone") String telephone) throws ProjectException {
		final var user = new User()
				.setUsername(username)
				.setPassword(password);
		final var store = new Store()
				.setName(name)
				.setTelephone(telephone)
				.setAddress(address)
				.setUser(user);
		storeService.enroll(store, user);
		return new Transporter().setMsg("注册成功");
	}

	/**
	 * 商家接单
	 */

	@SaCheckRole(value = {"store", "admin"}, mode = SaMode.OR)
	@GetMapping("/take/{orderId}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter takeOrder (@PathVariable("orderId") String orderIdString) throws ProjectException {
		var orderId = Integer.parseInt(orderIdString);
		var transporter = new Transporter();
		val select = orderService.select(orderId);
		val status = select.getStatus();
		select.setStatus(status + 1);

		transporter.addData("status", status + 1)
				.setMsg("接单成功");
		return transporter;
	}


	/**
	 * 商品列表显示：
	 * 显示商品信息
	 */
	@SaCheckRole(value = {"store", "admin"}, mode = SaMode.OR)
	@GetMapping("/show/{storeId}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter showProduct (@PathVariable("storeId") String storeIdString) throws ProjectException {
		var storeId = Integer.parseInt(storeIdString);
		var transporter = new Transporter();
		val select = storeService.select(storeId);
		val product = productService.select(storeId);
		transporter.addData("store", select);
		transporter.addData("product", product);
		return transporter;
	}

	/**
	 * 管理商家页
	 * 编辑
	 */
	@SaCheckRole("store")
	@GetMapping("/edit/{storeId}/{storeName}/{storeTel}/{storeAddress}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter edit (@PathVariable("storeId") String storeIdString,
	                         @PathVariable("storeName") String storeName,
	                         @PathVariable("storeTel") String storeTel,
	                         @PathVariable("storeAddress") String storeAddress) throws ProjectException {
		var storeId = Integer.parseInt(storeIdString);
		val select = storeService.select(storeId);
		select.setName(storeName)
				.setTelephone(storeTel)
				.setAddress(storeAddress);
		storeService.restore(select);
		return new Transporter().setMsg("编辑成功");
	}

	/**
	 * 管理商家页
	 * 删除
	 */
	@SaCheckRole("guest")
	@GetMapping("/delete/{storeId}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter delete (@PathVariable("storeId") String storeIdString) throws ProjectException {
		var storeId = Integer.parseInt(storeIdString);
		storeService.delete(storeId);
		return new Transporter().setMsg("删除成功");
	}

	/**
	 * 商家信息页
	 * 商家信息显示
	 */
	@SaCheckRole("guest")
	@GetMapping("/info/{storeId}")
	public Transporter showInfo (@PathVariable("storeId") Integer storeId) throws ProjectException {
		val select = storeService.select(storeId);
		Transporter transporter = new Transporter();
		transporter.addData("store", select)
				.setMsg("查询成功");
		return transporter;
	}


	/**
	 * 商品列表显示：
	 * 显示商品信息
	 */
	@SaCheckRole("store")
	@GetMapping("/showproduct/{storeId}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter showProduct(@PathVariable("storeId") Integer storeId) throws ProjectException{
		var transporter = new Transporter();
		var store = storeService.select(storeId);
		var product=productService.select(store);
		transporter.addData("product",product);
		return transporter;
	}

	/**
	 * 商品列表显示：
	 * 编辑商品信息
	 */
	@SaCheckRole("store")
	@GetMapping("/edit/{productId}/{productName}/{productPrice}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter editProduct(@PathVariable("productId") Integer productId,
									@PathVariable("productName") String productName,
								   @PathVariable("productPrice") BigDecimal productPrice) throws ProjectException{
		var transporter = new Transporter();
		var select = productService.select(productId);
		select.setName(productName)
				.setPrice(productPrice);
		return transporter.setMsg("修改成功");
	}
	/**
	 * 商品列表显示：
	 * 删除商品信息
	 */
	@SaCheckRole("store")
	@GetMapping("/delete/{storeId}/{productId}/")
	@Transactional(rollbackFor = Exception.class)
	public Transporter deleteProduct(@PathVariable("storeId") Integer storeId,
								   @PathVariable("productId") Integer productId ) throws ProjectException{
		var transporter = new Transporter();
		productService.delete(productId);
		return transporter.setMsg("成功删除");
	}

}

