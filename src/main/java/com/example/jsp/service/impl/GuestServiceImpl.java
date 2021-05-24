package com.example.jsp.service.impl;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.manager.toservice.AddressManager;
import com.example.jsp.manager.toservice.GuestManager;
import com.example.jsp.pojo.Guest;
import com.example.jsp.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author 橙鼠鼠
 */
@Service
public class GuestServiceImpl implements GuestService {
	GuestManager guestManager;
	AddressManager addressManager;

	@Autowired
	public void setGuestManager (GuestManager guestManager) {
		this.guestManager = guestManager;
	}

	@Autowired
	public void setAddressManager (AddressManager addressManager) {
		this.addressManager = addressManager;
	}

	@Override
	public void create (Guest target) throws ProjectException {
		try {
			guestManager.insert(target);
		} catch (SonElementNotExistExceptionOld sonElementNotExistExceptionOld) {
			throw new ProjectException(Arrays.toString(sonElementNotExistExceptionOld.getStackTrace()),304);
		}
	}
}
