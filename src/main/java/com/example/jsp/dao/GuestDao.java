package com.example.jsp.dao;

import com.example.jsp.pojo.Guest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface GuestDao {
    int save(@Param("target") Guest target);

    void delete(@Param("id") int id);

    Guest selectById(@Param("id") int id);

    List<Guest> selectAll();

    void update(@Param("target") Guest target);

    Integer getId(@Param("guest") Guest guest);

}
