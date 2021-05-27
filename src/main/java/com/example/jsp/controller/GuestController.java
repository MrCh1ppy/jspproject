package com.example.jsp.controller;

import com.example.jsp.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestController {
	private GuestService guestService;

	@Autowired
	public void setGuestService (GuestService guestService) {
		this.guestService = guestService;
	}
}
