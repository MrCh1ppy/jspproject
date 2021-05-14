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
    int save(@Param("tUser") User user);

    void delete(@Param("tId") int tid);

    User selectById(@Param("tId") int tid);

    List<User> selectAll();

    void update(@Param("tUser") User tUser);
}
