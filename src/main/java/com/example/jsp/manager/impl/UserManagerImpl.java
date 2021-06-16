package com.example.jsp.manager.impl;

import com.example.jsp.commons.oldexception.manager.ElementAlreadyExistExceptionOld;
import com.example.jsp.dao.UserDao;
import com.example.jsp.manager.todao.UserManagerToDao;
import com.example.jsp.manager.toservice.UserManager;
import com.example.jsp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class UserManagerImpl implements UserManager, UserManagerToDao {
	private UserDao userDao;

	@Autowired
	public void setUserDao (UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void destroy (Integer id) {
		del(id);
	}

	@Override
	public void destroy (User user) {
		del(user.getId());
	}

	@Override
	public Integer restore (User user) {
		User temp = getId(user);
		if (temp == null) {
			update(user);
			return 0;
		}
		user.setId(temp.getId());
		return temp.getId();
	}

	@Override
	public User select (Integer id) {
		return userDao.selectById(id);
	}

	@Override
	public List<User> select () {
		return userDao.selectAll();
	}

	@Override
	public Integer insert (User targetUser) throws ElementAlreadyExistExceptionOld {
		if (findByUsername(targetUser.getUsername()) != null) {
			throw new ElementAlreadyExistExceptionOld();
		}
		var tempUser = userDao.getId(targetUser);
		if (tempUser != null) {
			targetUser.setId(tempUser.getId());
			userDao.update(targetUser);
			/*没必要专门写一个更新update字段的函数,因为mysql的底层是delete/insert 所以没有意义*/
			return tempUser.getId();
		}
		if (targetUser.getEnabled() == null) {
			targetUser.setEnabled(1);
		}
		save(targetUser);
		return targetUser.getId();
	}

	@Override
	public Integer save (User targetUser) {
		userDao.save(targetUser);
		return targetUser.getId();
	}

	@Override
	public void del (Integer id) {
		userDao.delete(id);
	}

	@Override
	public void update (User user) {
		userDao.update(user);
	}

	@Override
	public User getId (User target) {
		return userDao.getId(target);
	}

	@Override
	public Boolean isNotExist (Integer id) {
		return userDao.selectById(id) == null;
	}

	@Override
	public Integer findByUsername (String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findUserByUsername (String username) {
		return userDao.findUserByUsername(username);
	}

	@Override
	public Boolean isNotExist (String username) {
		return userDao.findByUsername(username) == null;
	}
}
