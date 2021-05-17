package com.example.jsp.service.impl;

import com.example.jsp.commons.exception.login.ErrorPassWordException;
import com.example.jsp.commons.exception.login.LoginException;
import com.example.jsp.commons.exception.login.UsernameNotExistException;
import com.example.jsp.commons.model.Token;
import com.example.jsp.manager.toservice.DeliverManager;
import com.example.jsp.manager.toservice.GuestManager;
import com.example.jsp.manager.toservice.StoreManager;
import com.example.jsp.manager.toservice.UserManager;
import com.example.jsp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 橙鼠鼠
 */
@Service
public class LoginServiceImpl implements LoginService {
	private StoreManager storeManager;
	private DeliverManager deliverManager;
	private UserManager userManager;
	private GuestManager guestManager;

	@Autowired
	public void setStoreManager (StoreManager storeManager) {
		this.storeManager = storeManager;
	}

	@Autowired
	public void setDeliverManager (DeliverManager deliverManager) {
		this.deliverManager = deliverManager;
	}

	@Autowired
	public void setUserManager (UserManager userManager) {
		this.userManager = userManager;
	}

	@Autowired
	public void setGuestManager (GuestManager guestManager) {
		this.guestManager = guestManager;
	}

	@Override
	public void login (Token token)throws LoginException {
		Integer userid = userManager.findByUsername(token.getUsername());
		if(userid==null){
			throw new UsernameNotExistException();
		}
		var user = userManager.select(userid);
		if(!token.getPassword().equals(user.getPassword())){
			throw new ErrorPassWordException();
		}
		if(guestManager.isGuest(userid)){
			token.setUserType("guest");
		}else if(deliverManager.isDeliver(userid)){
			token.setUserType("deliver");
		}else if(storeManager.isStore(userid)){
			token.setUserType("store");
		}else{
			token.setUserType("admin");
		}
	}
}
