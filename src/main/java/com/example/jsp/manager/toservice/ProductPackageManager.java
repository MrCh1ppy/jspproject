package com.example.jsp.manager.toservice;

import com.example.jsp.pojo.ProductPackage;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface ProductPackageManager {
	Integer insert (ProductPackage target);

	void destroy (Integer id);

	void destroy (ProductPackage productPackage);

	void deleteByOrderId (Integer id);

	ProductPackage select (Integer id);

	Integer restore (ProductPackage target);

	List<ProductPackage> selectByOrderId (Integer id);

	Integer getId (ProductPackage productPackage);

	Boolean isNotExist (Integer id);
}
