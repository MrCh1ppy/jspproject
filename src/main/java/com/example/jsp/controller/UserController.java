package com.example.jsp.controller;


import com.example.jsp.commons.model.Transporter;
import com.example.jsp.pojo.User;
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
@RequestMapping("/user")
public class UserController {
	private UserService userService;

	@Autowired
	public void setUserService (UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login/{type}/{username}/{password}")
	public Transporter login(@PathVariable("username")String username,
	                                 @PathVariable("password")String password,
	                                 @PathVariable("type")String type) throws Exception{
		var transporter = new Transporter();
		var user = new User().setPassword(password).setUsername(username);
		String login = userService.login(user, type);
		transporter.setCode(0).addData("id",login);
		return transporter;
	}


}
