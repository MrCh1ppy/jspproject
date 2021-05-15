package com.example.jsp.manager.todao;

import com.example.jsp.pojo.Order;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface OrderManagerToDao {
    int save(Order target);

    void delete(int id);

    Order select(int id);

    List<Order> select();

    void update(Order target);

    Integer getId(Order target);
}
