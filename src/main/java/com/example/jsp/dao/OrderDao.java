package com.example.jsp.dao;

import com.example.jsp.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface OrderDao {
    int save(@Param("target") Order target);

    void delete(@Param("id") int id);

    void selectById(@Param("id") int id);

    void selectAll();

    void update(@Param("target") Order target);
}
