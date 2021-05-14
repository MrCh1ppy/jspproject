package com.example.jsp.dao;

import com.example.jsp.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface OrderDao {
    int save(Order target);

    void delete(int id);

    void selectById(int id);

    void selectAll();

    void update(Order target);
}
