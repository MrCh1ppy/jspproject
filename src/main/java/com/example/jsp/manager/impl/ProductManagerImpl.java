package com.example.jsp.manager.impl;

import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.dao.ProductDao;
import com.example.jsp.manager.todao.ProductManagerToDao;
import com.example.jsp.manager.toservice.ProductManager;
import com.example.jsp.manager.toservice.StoreManager;
import com.example.jsp.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class ProductManagerImpl implements ProductManagerToDao, ProductManager {
	private StoreManager storeManager;
	private ProductDao productDao;

	@Autowired
	public void setStoreManager (StoreManager storeManager) {
		this.storeManager = storeManager;
	}

	@Autowired
	public void setProductDao (ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public Integer insert (Product target) throws SonElementNotExistExceptionOld {
		Boolean notExist = storeManager.isNotExist(target.getStore().getId());
		if (Boolean.TRUE.equals(notExist)) {
			throw new SonElementNotExistExceptionOld();
		}
		Integer id = getId(target);
		if (id == null) {
			save(target);
			return target.getId();
		}
		target.setId(id);
		return target.getId();
	}

	@Override
	public Integer save (Product target) {
		productDao.save(target);
		return target.getId();
	}

	@Override
	public void delete (Integer id) {
		productDao.delete(id);
	}

	@Override
	public Product select (Integer id) {
		return productDao.selectById(id);
	}

	@Override
	public List<Product> select () {
		return productDao.selectAll();
	}

	@Override
	public void update (Product target) {
		productDao.update(target);
	}

	@Override
	public Integer getId (Product product) {
		return productDao.getId(product);
	}

	@Override
	public List<Product> selectByStore (int storeId) {
		return productDao.selectByStoreId(storeId);
	}

	@Override
	public void destroy (Integer id) {
		delete(id);
	}

	@Override
	public void destroy (Product target) {
		destroy(target.getId());
	}

	@Override
	public Integer restore (Product target) throws SonElementNotExistExceptionOld {
		Integer id = getId(target);
		if (id == null) {
			Boolean notExist = storeManager.isNotExist(target.getStore().getId());
			if (Boolean.TRUE.equals(notExist)) {
				throw new SonElementNotExistExceptionOld();
			}
			update(target);
			return 0;
		}
		target.setId(id);
		return target.getId();
	}

	@Override
	public Boolean isNotExist (Integer id) {
		return select(id) == null;
	}
}
