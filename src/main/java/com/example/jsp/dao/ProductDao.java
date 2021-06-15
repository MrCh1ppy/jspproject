package com.example.jsp.dao;

import com.example.jsp.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface ProductDao {
	void save (@Param("target") Product target);

	void delete (@Param("id") Integer id);

	Product selectById (@Param("id") Integer id);

	List<Product> selectAll ();

	void update (@Param("target") Product target);

	Integer getId (@Param("target") Product product);

	List<Product> selectByStoreId (@Param("storeId") Integer storeId);
}
