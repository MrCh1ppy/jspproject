package com.example.jsp.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.example.jsp.commons.model.Transporter;
import com.example.jsp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 橙鼠鼠
 */
@RestController
@RequestMapping("/product")
public class ProductController {
	private ProductService productService;

	@Autowired
	public void setProductService (ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/show")
	@SaCheckRole(value = {"guest", "store", "admin"}, mode = SaMode.OR)
	public Transporter selectAll () {
		final var select = productService.select();
		final var transporter = new Transporter();
		transporter.addData("productList", select);
		return transporter;
	}

	@GetMapping("/show/{storeId}")
	@SaCheckRole(value = {"guest", "store", "admin"}, mode = SaMode.OR)
	public Transporter selectAll (@PathVariable("storeId") String storeIdString) {
		var storeId = Integer.parseInt(storeIdString);
		final var select = productService.selectByStore(storeId);
		final var transporter = new Transporter();
		transporter.addData("productList", select);
		return transporter;
	}
}
