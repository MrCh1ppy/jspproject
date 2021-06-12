package com.example.jsp.controller;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.model.Transporter;
import com.example.jsp.pojo.Store;
import com.example.jsp.pojo.User;
import com.example.jsp.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 橙鼠鼠
 */
@RestController
@RequestMapping("/store")
public class StoreController {
	private StoreService service;

	@Autowired
	public void setService (StoreService service) {
		this.service = service;
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
		service.enroll(store, user);
		return new Transporter().setMsg("注册成功");
	}
}

