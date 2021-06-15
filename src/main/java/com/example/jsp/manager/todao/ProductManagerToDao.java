package com.example.jsp.manager.todao;

import com.example.jsp.pojo.Product;

import java.util.List;

/**
 * @author 橙鼠鼠
 */

public interface ProductManagerToDao {
	Integer save (Product target);

	void delete (Integer id);

	Product select (Integer id);

	List<Product> select ();

	void update (Product target);

	Integer getId (Product product);

	List<Product> selectByStore (int storeId);
}
