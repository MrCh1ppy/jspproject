package com.example.jsp.manager.toservice;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.pojo.Order;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface OrderManager {
    Integer insert(Order target) throws ProjectException;

    void destroy(Integer id);

    void destroy(Order order);

    Order select(Integer id);

    List<Order> select();

    Integer restore(Order target) throws ProjectException;

    Integer getId(Order target);

    Boolean isNotExist(Integer id);
}
