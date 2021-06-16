package com.example.jsp.dao;

import com.example.jsp.pojo.ProductPackage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface ProductPackageDao {
	void save (@Param("target") ProductPackage target);

	void delete (@Param("id") Integer id);

	void deleteByOrderId (@Param("id") Integer id);

	ProductPackage selectById (@Param("id") Integer id);

	void update (@Param("target") ProductPackage target);

	List<ProductPackage> selectByOrderId (@Param("id") Integer id);

	Integer getId (@Param("target") ProductPackage productPackage);
}
