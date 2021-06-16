package com.example.jsp.dao;

import com.example.jsp.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface UserDao {

	void save (@Param("user") User user);

	void delete (@Param("tid") Integer tid);

	User selectById (@Param("tid") Integer tid);

	List<User> selectAll ();

	void update (@Param("user") User user);

	User getId (@Param("user") User user);

	Integer findByUsername (@Param("username") String username);

	User findUserByUsername (@Param("username") String username);
}
