package com.example.jsp.dao;

import com.example.jsp.pojo.Guest;
import com.example.jsp.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface GuestDao {
	void save (@Param("target") Guest target);

	void delete (@Param("id") Integer id);

	Guest selectById (@Param("id") Integer id);

	List<Guest> selectAll ();

	void update (@Param("target") Guest target);

	Integer getId (@Param("target") Guest guest);

	Integer findIdByLoginUser (@Param("id") int userId);

	User findUserByUserName (@Param("username") String username);


}
