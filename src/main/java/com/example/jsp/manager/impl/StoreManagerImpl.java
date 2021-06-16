package com.example.jsp.manager.impl;

import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.dao.StoreDao;
import com.example.jsp.manager.todao.StoreManagerToDao;
import com.example.jsp.manager.toservice.ProductManager;
import com.example.jsp.manager.toservice.StoreManager;
import com.example.jsp.manager.toservice.UserManager;
import com.example.jsp.pojo.Product;
import com.example.jsp.pojo.Store;
import com.example.jsp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class StoreManagerImpl implements StoreManagerToDao, StoreManager {
	private UserManager userManager;
	private StoreDao storeDao;
	private ProductManager productManager;

	@Autowired
	public void setUserManager (UserManager userManager) {
		this.userManager = userManager;
	}

	@Autowired
	public void setStoreDao (StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	@Override
	public Integer insert (Store store) throws SonElementNotExistExceptionOld {
		Boolean notExist = userManager.isNotExist(store.getUser().getId());
		if (Boolean.TRUE.equals(notExist)) {
			throw new SonElementNotExistExceptionOld("Store.user");
		}

		Integer id = storeDao.getId(store);
		if (id == null) {
			save(store);
			return store.getId();
		}
		store.setId(id);
		return store.getId();
	}

	@Override
	public Integer save (Store store) {
		storeDao.save(store);
		return store.getId();
	}

	@Override
	public void delete (Integer id) {
		storeDao.delete(id);
	}

	@Override
	public Store select (Integer id) {
		return storeDao.selectById(id);
	}

	@Override
	public List<Store> select () {
		return storeDao.selectAll();
	}

	@Override
	public void update (Store store) {
		storeDao.update(store);
	}

	@Override
	public Integer getId (Store store) {
		return storeDao.getId(store);
	}

	@Override
	public Boolean isStore (int userId) {
		return storeDao.findIdByLoginUser(userId) != null;
	}

	@Override
	public User findUserByUserName (String username) {
		return storeDao.findUserByUserName(username);
	}

	@Override
	public Integer selectByUserId (Integer userId) {
		return storeDao.findIdByLoginUser(userId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void destroy (Integer id) {
		destroy(select(id));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void destroy (Store store) {
		delete(store.getId());
		userManager.destroy(store.getUser().getId());
	}

	@Override
	public Integer restore (Store store) throws SonElementNotExistExceptionOld {
		Integer id = getId(store);
		if (id == null) {
			Boolean notExist = userManager.isNotExist(store.getUser().getId());
			if (Boolean.TRUE.equals(notExist)) {
				throw new SonElementNotExistExceptionOld();
			}
			storeDao.update(store);
			return 0;
		}
		store.setId(id);
		return store.getId();
	}

	@Override
	public Boolean isNotExist (Integer id) {
		return storeDao.selectById(id) == null;
	}

	@Override
	public List<Product> selectHavingProduct (Store store) {
		return productManager.selectByStore(store.getId());
	}

	@Override
	public List<Product> selectHavingProduct (Integer storeId) {
		return productManager.selectByStore(storeId);
	}
}
