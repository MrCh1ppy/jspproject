package com.example.jsp.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.oldexception.login.ErrorPassWordExceptionOld;
import com.example.jsp.commons.oldexception.login.UsernameNotExistExceptionOld;
import com.example.jsp.commons.oldexception.manager.ElementAlreadyExistExceptionOld;
import com.example.jsp.manager.toservice.DeliverManager;
import com.example.jsp.manager.toservice.GuestManager;
import com.example.jsp.manager.toservice.StoreManager;
import com.example.jsp.manager.toservice.UserManager;
import com.example.jsp.pojo.User;
import com.example.jsp.service.UserService;
import com.example.jsp.utils.login.LoginId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * @author 橙鼠鼠
 */
@Service
public class UserServiceImpl implements UserService {
	private UserManager userManager;
	private StoreManager storeManager;
	private GuestManager guestManager;
	private DeliverManager deliverManager;

	@Autowired
	public void setUserManager (UserManager userManager) {
		this.userManager = userManager;
	}

	@Autowired
	public void setStoreManager (StoreManager storeManager) {
		this.storeManager = storeManager;
	}

	@Autowired
	public void setGuestManager (GuestManager guestManager) {
		this.guestManager = guestManager;
	}

	@Autowired
	public void setDeliverManager (DeliverManager deliverManager) {
		this.deliverManager = deliverManager;
	}


	@Override
	public void create (User user) throws ProjectException {
		try {
			userManager.insert(user);
		} catch (ElementAlreadyExistExceptionOld e) {
			throw new ProjectException(Arrays.toString(e.getStackTrace()), 301);
		}
	}


	@Override
	public void delete (User user) throws ProjectException {
		userManager.destroy(user);
	}


	@Override
	public void delete (Integer userId) throws ProjectException {
		userManager.destroy(userId);
	}


	@Override
	public void restore (User user) throws ProjectException {
		userManager.restore(user);
	}

	@Override
	public String login (User user, String type) throws UsernameNotExistExceptionOld, ErrorPassWordExceptionOld, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		User loginUser;
		Integer targetId = null;
		switch (type) {
			case "guest":
				loginUser = guestManager.findUserByUserName(user.getUsername());
				if (loginUser != null) {
					targetId = guestManager.selectByUserId(loginUser.getId());
				}
				break;
			case "store":
				loginUser = storeManager.findUserByUserName(user.getUsername());
				if (loginUser != null) {
					targetId = storeManager.selectByUserId(loginUser.getId());
				}
				break;
			case "deliver":
				loginUser = deliverManager.findUserByUserName(user.getUsername());
				if (loginUser != null) {
					targetId = deliverManager.selectByUserId(loginUser.getId());
				}
				break;
			case "admin":
				loginUser = userManager.findUserByUsername(user.getUsername());
				if (loginUser != null) {
					targetId = deliverManager.selectByUserId(loginUser.getId());
				}
				break;
			default:
				throw new UsernameNotExistExceptionOld();
		}
		if (loginUser == null) {
			throw new UsernameNotExistExceptionOld();
		}
		if (!loginUser.getPassword().equals(user.getPassword())) {
			throw new ErrorPassWordExceptionOld(loginUser.getPassword() + " " + user.getPassword());
		}
		var id = new LoginId(type).toStringByReflect();
		StpUtil.setLoginId(id);
		return id + "_" + targetId;
	}


	@Override
	public User select (Integer userId) {
		return userManager.select(userId);
	}
}
