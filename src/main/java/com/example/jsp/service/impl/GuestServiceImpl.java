package com.example.jsp.service.impl;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.manager.toservice.AddressManager;
import com.example.jsp.manager.toservice.GuestManager;
import com.example.jsp.pojo.Address;
import com.example.jsp.pojo.Guest;
import com.example.jsp.pojo.User;
import com.example.jsp.service.GuestService;
import com.example.jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author 橙鼠鼠
 */
@Service
public class GuestServiceImpl implements GuestService {
	private GuestManager guestManager;
	private UserService userService;
	private AddressManager addressManager;

	@Autowired
	public void setUserService (UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setGuestManager (GuestManager guestManager) {
		this.guestManager = guestManager;
	}

	@Autowired
	public void setAddressManager(AddressManager addressManager) { this.addressManager = addressManager; }

	@Override
	public void create (Guest target) throws ProjectException {
		try {
			guestManager.insert(target);
		} catch (SonElementNotExistExceptionOld sonElementNotExistExceptionOld) {
			throw new ProjectException(sonElementNotExistExceptionOld.toString(), 304);
		}
	}

	@Override
	public void delete (Guest target) throws ProjectException {
		guestManager.destroy(target);
	}

	@Override
	public void delete (Integer guestId) throws ProjectException {
		guestManager.destroy(guestId);
	}

	@Override
	public void restore (Guest guest) throws ProjectException {
		try {
			guestManager.restore(guest);
		} catch (SonElementNotExistExceptionOld sonElementNotExistExceptionOld) {
			throw new ProjectException(sonElementNotExistExceptionOld.toString(), 304);
		}

	}

	@Override
	public Guest select (Integer guestId) throws ProjectException {
		return guestManager.select(guestId);
	}

	@Override
	public List<Guest> select () throws ProjectException {
		return guestManager.select();
	}

	@Override
	public void enroll (Guest guest, User user) throws ProjectException {
		userService.create(user);
		create(guest);
	}


	@Override
	public List<Address> apart (String addresses) {
		List<Address> addressList = new ArrayList<>();
		String[] address = addresses.split("_");
		for (String s : address) {
			addressList.add(new Address().setAddressString(s));
		}
		return addressList;
	}

	@Override
	public void addAddress (Guest guest, Address address) throws ProjectException {

		try {
			address.setGuestId(guest.getId());
			guest.getAddresses().add(address);
			guestManager.restore(guest);
		} catch (SonElementNotExistExceptionOld sonElementNotExistExceptionOld) {
			throw new ProjectException(sonElementNotExistExceptionOld.toString(), 304);
		}
	}

	@Override
	public Address getAddress (Integer addressId) {
		return addressManager.select(addressId);
	}
}
