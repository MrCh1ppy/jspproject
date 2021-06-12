package com.example.jsp.dao;

import com.example.jsp.pojo.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface OrderInfoDao {
	void save (@Param("target") OrderInfo target);

	void delete (@Param("target") Integer id);

	OrderInfo selectById (@Param("target") Integer id);

	void update (@Param("target") OrderInfo target);

	List<OrderInfo> selectByOrderId (@Param("id") Integer id);

	Integer getId (@Param("target") OrderInfo orderInfo);

	void deleteByOrder (@Param("id") int id);
}
