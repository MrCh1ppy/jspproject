package com.example.jsp.manager.impl;

import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.dao.DeliverDao;
import com.example.jsp.manager.todao.DeliverManagerToDao;
import com.example.jsp.manager.toservice.DeliverManager;
import com.example.jsp.manager.toservice.UserManager;
import com.example.jsp.pojo.Deliver;
import com.example.jsp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class DeliverManagerImpl implements DeliverManagerToDao, DeliverManager {
	private UserManager userManager;
	private DeliverDao deliverDao;

	@Autowired
	public void setUserManager (UserManager userManager) {
		this.userManager = userManager;
	}

	@Autowired
	public void setDeliverDao (DeliverDao deliverDao) {
		this.deliverDao = deliverDao;
	}

	@Override
	public Integer insert (Deliver deliver) throws SonElementNotExistExceptionOld {
		Boolean notExist = userManager.isNotExist(deliver.getLoginUser().getId());
		if (Boolean.TRUE.equals(notExist)) {
			throw new SonElementNotExistExceptionOld("deliver.User");
		}
		Integer id = getId(deliver);
		if (id == null) {
			save(deliver);
			return deliver.getId();
		}
		deliver.setId(id);
		return deliver.getId();
	}

	@Override
	public Integer save (Deliver deliver) {
		deliverDao.save(deliver);
		return deliver.getId();
	}

	@Override
	public List<Deliver> select () {
		return deliverDao.selectAll();
	}

	@Override
	public Deliver select (Integer id) {
		return deliverDao.selectById(id);
	}

	@Override
	public Integer getId (Deliver deliver) {
		return deliverDao.getId(deliver);
	}

	@Override
	public void update (Deliver deliver) {
		deliverDao.update(deliver);
	}

	@Override
	public void delete (Integer id) {
		deliverDao.delete(id);
	}

	@Override
	public Boolean isDeliver (int userId) {
		return deliverDao.findIdByLoginUser(userId) != null;
	}

	@Override
	public User findUserByUserName (String username) {
		return deliverDao.findUserByUserName(username);
	}

	@Override
	public Integer selectByUserId (Integer userId) {
		return deliverDao.findIdByLoginUser(userId);
	}

	@Override
	public Integer restore (Deliver deliver) throws SonElementNotExistExceptionOld {
		Integer id = getId(deliver);
		if (id == null) {
			Boolean notExist = userManager.isNotExist(deliver.getLoginUser().getId());
			if (Boolean.TRUE.equals(notExist)) {
				throw new SonElementNotExistExceptionOld("deliver.user");
			}
			update(deliver);
			return 0;
		}
		deliver.setId(id);
		return deliver.getId();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void destroy (Integer id) {
		destroy(deliverDao.selectById(id));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void destroy (Deliver deliver) {
		delete(deliver.getId());
		userManager.destroy(deliver.getLoginUser().getId());
	}

	@Override
	public Boolean isNotExist (Integer id) {
		return deliverDao.selectById(id) == null;
	}
}
