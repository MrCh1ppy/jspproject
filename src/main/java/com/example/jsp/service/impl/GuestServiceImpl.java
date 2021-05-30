package com.example.jsp.service.impl;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.manager.toservice.GuestManager;
import com.example.jsp.pojo.Address;
import com.example.jsp.pojo.Guest;
import com.example.jsp.pojo.User;
import com.example.jsp.service.GuestService;
import com.example.jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

/**
 * @author 橙鼠鼠
 */
@Service
public class GuestServiceImpl implements GuestService {
	GuestManager guestManager;
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setGuestManager (GuestManager guestManager) {
		this.guestManager = guestManager;
	}



	@Override
	public void create (Guest target) throws ProjectException {
		try {
			guestManager.insert(target);
		} catch (SonElementNotExistExceptionOld sonElementNotExistExceptionOld) {
			throw new ProjectException(sonElementNotExistExceptionOld.toString(),304);
		}
	}

	@Override
	public void enroll (Guest guest, User user) throws ProjectException {
		userService.create(user);
		create(guest);
	}
	@Override
	public  List<Address> apart(String addresses) {
		List<Address>  addressList= new ArrayList<>();
		String[] address=addresses.split("_");
		for (String s : address) {
			addressList.add(new Address().setAddressString(s));
		}
		return addressList;
	}
}
