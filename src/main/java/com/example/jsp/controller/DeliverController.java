package com.example.jsp.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.model.Transporter;
import com.example.jsp.pojo.Deliver;
import com.example.jsp.pojo.User;
import com.example.jsp.service.DeliverService;
import com.example.jsp.service.OrderService;
import com.example.jsp.service.UserService;
import lombok.val;
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
@RequestMapping("/deliver")
public class DeliverController {
	DeliverService deliverService;
	UserService userService;
	OrderService orderService;


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
		var user = new User().setUsername(username).setPassword(password);
		userService.create(user);
		var deliver = new Deliver().setLoginUser(user).setTelephone(telephone).setName(deliverName);
		deliverService.create(deliver);
		return new Transporter().setMsg("注册成功");
	}


	/**
	 * 管理骑手页面
	 * 骑手列表显示
	 */
	@SaCheckLogin
	@GetMapping("/show")
	public Transporter showDeliver () throws ProjectException {
		var transporter = new Transporter();
		val select = deliverService.select();
		return transporter.addData("deliver", select).setMsg("查询成功");
	}

	/**
	 * 管理骑手页
	 * 编辑
	 */
	@SaCheckRole(value = {"admin", "deliver"}, mode = SaMode.OR)
	@GetMapping("/edit/{deliverId}/{deliverName}/{deliverTel}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter edit (@PathVariable("deliverId") String deliverIdString,
	                         @PathVariable("deliverName") String deliverName,
	                         @PathVariable("deliverTel") String deliverTel) throws ProjectException {
		var deliverId = Integer.parseInt(deliverIdString);
		var select = deliverService.select(deliverId);
		select.setName(deliverName)
				.setTelephone(deliverTel);
		deliverService.restore(select);
		return new Transporter().setMsg("编辑成功");
	}

	/**
	 * 管理骑手页
	 * 删除
	 */

	@SaCheckRole(value = {"admin", "deliver"}, mode = SaMode.OR)
	@GetMapping("/delete/{deliverId}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter delete (@PathVariable("deliverId") String deliverId) throws ProjectException {
		deliverService.delete(Integer.parseInt(deliverId));
		return new Transporter().setMsg("删除成功");
	}

	/**
	 * 管理骑手页
	 * 骑手接单
	 */
	@SaCheckRole(value = {"admin", "deliver"}, mode = SaMode.OR)
	@GetMapping("/take/{orderId}/{deliverId}")
	public Transporter takeOrder (@PathVariable("orderId") String orderIdString, @PathVariable("deliverId") String deliverIdString) throws ProjectException {
		var deliverId = Integer.parseInt(deliverIdString);
		var orderId = Integer.parseInt(orderIdString);
		var transporter = new Transporter();

		val order = orderService.select(orderId);
		var deliver = deliverService.select(deliverId);
		order.setStatus(order.getStatus() + 1).setDeliver(deliver);
		orderService.restore(order);

		transporter.addData("order", order)
				.setMsg(deliver.getName() + "接单成功");
		return transporter;
	}


	/**
	 * 骑手信息页：
	 * 骑手信息显示
	 */
	@SaCheckRole(value = {"admin", "deliver"}, mode = SaMode.OR)
	@GetMapping("/show/{deliverId}")
	@Transactional(rollbackFor = Exception.class)
	public Transporter showInfo (@PathVariable("deliverId") String deliverId) throws ProjectException {
		var transporter = new Transporter();
		Integer id = Integer.parseInt(deliverId);
		var select = deliverService.select(id);
		transporter.addData("deliver", select);
		return transporter.setMsg("查询成功");
	}
}

