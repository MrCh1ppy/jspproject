package com.example.jsp.manager.impl;

import com.example.jsp.dao.ProductPackageDao;
import com.example.jsp.manager.todao.ProductPackageManagerToDao;
import com.example.jsp.manager.toservice.ProductPackageManager;
import com.example.jsp.pojo.ProductPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class ProductPackageManagerImpl implements ProductPackageManagerToDao, ProductPackageManager {
	private ProductPackageDao productPackageDao;

	@Autowired
	public void setProductPackageDao (ProductPackageDao productPackageDao) {
		this.productPackageDao = productPackageDao;
	}

	@Override
	public Integer insert (ProductPackage target) {
		Integer id = getId(target);
		if (id == null) {
			save(target);
			return target.getId();
		}
		target.setId(id);
		return target.getId();
	}

	@Override
	public Integer save (ProductPackage target) {
		productPackageDao.save(target);
		return target.getId();
	}

	@Override
	public void delete (Integer id) {
		productPackageDao.delete(id);
	}

	@Override
	public void deleteByOrderId (Integer id) {
		productPackageDao.deleteByOrderId(id);
	}

	@Override
	public ProductPackage select (Integer id) {
		return productPackageDao.selectById(id);
	}

	@Override
	public void update (ProductPackage target) {
		productPackageDao.update(target);
	}

	@Override
	public List<ProductPackage> selectByOrderId (Integer id) {
		return productPackageDao.selectByOrderId(id);
	}

	@Override
	public Integer getId (ProductPackage productPackage) {
		return productPackageDao.getId(productPackage);
	}

	@Override
	public void destroy (Integer id) {
		delete(id);
	}

	@Override
	public void destroy (ProductPackage productPackage) {
		delete(productPackage.getId());
	}

	@Override
	public Integer restore (ProductPackage target) {
		Integer id = getId(target);
		if (id == null) {
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
