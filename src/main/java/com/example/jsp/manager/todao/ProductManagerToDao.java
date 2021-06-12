package com.example.jsp.manager.todao;

import com.example.jsp.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 橙鼠鼠
 */

public interface ProductManagerToDao {
	Integer save (@Param("target") Product target);

	void delete (@Param("id") Integer id);

	Product select (@Param("id") Integer id);

	List<Product> select ();

	void update (@Param("target") Product target);

	Integer getId (@Param("target") Product product);
}
