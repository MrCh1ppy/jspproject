package com.example.jsp.dao;

import com.example.jsp.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface OrderDao {
	void save (@Param("target") Order target);

	void delete (@Param("id") Integer id);

	Order selectById (@Param("id") Integer id);

	List<Order> selectAll ();

	void update (@Param("target") Order target);

	Integer getId (@Param("target") Order target);

	List<Order> selectByStatus (@Param("status") Integer target);
}
