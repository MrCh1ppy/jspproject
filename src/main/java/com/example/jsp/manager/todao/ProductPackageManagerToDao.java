package com.example.jsp.manager.todao;

import com.example.jsp.pojo.ProductPackage;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface ProductPackageManagerToDao {
	Integer save (ProductPackage target);

	void delete (Integer id);

	void deleteByOrderId (Integer id);

	ProductPackage select (Integer id);

	void update (ProductPackage target);

	List<ProductPackage> selectByOrderId (Integer id);

	Integer getId (ProductPackage productPackage);
}
