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
    Integer save(@Param("tUser") User user);

    void delete(@Param("tId") Integer tid);

    User selectById(@Param("tId") Integer tid);

    List<User> selectAll();

    void update(@Param("tUser") User tUser);

    User getId(@Param("user") User user);

    Integer findByUsername(@Param("username")String username);
}
