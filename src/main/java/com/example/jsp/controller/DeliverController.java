package com.example.jsp.controller;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.model.Transporter;
import com.example.jsp.pojo.Deliver;
import com.example.jsp.pojo.User;
import com.example.jsp.service.DeliverService;
import com.example.jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 橙鼠鼠
 */
@RestController
@RequestMapping("/deliver")
public class DeliverController {
	DeliverService deliverService;
	UserService userService;

	@Autowired
	public void setDeliverService (DeliverService deliverService) {
		this.deliverService = deliverService;
	}

	@Autowired
	public void setUserService (UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/enroll/{username}/{password}/{deliverName}/{telephone}")
	public Transporter enroll (@PathVariable("username") String username,
	                           @PathVariable("password") String password,
	                           @PathVariable("deliverName") String deliverName,
	                           @PathVariable("telephone") String telephone) throws ProjectException {
		var user = new User().setUsername(username).setPassword(password).setEnabled(1);
		userService.create(user);
		var deliver = new Deliver().setLoginUser(user).setTelephone(telephone).setName(deliverName);
		deliverService.create(deliver);
		return new Transporter();
	}
}
