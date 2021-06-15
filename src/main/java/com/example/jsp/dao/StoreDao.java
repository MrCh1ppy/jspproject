package com.example.jsp.dao;

import com.example.jsp.pojo.Store;
import com.example.jsp.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface StoreDao {
	void save (@Param("store") Store store);

	void delete (@Param("id") Integer id);

	Store selectById (@Param("id") Integer id);

	List<Store> selectAll ();

	void update (@Param("store") Store store);

	Integer getId (@Param("store") Store store);

	Integer findIdByLoginUser (@Param("id") int userId);

	User findUserByUserName (@Param("username") String username);
}