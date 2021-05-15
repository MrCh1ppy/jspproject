package com.example.jsp.manager.toservice;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.pojo.Order;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface OrderManager {
    int insert(Order target)throws ProjectException;

    void destroy(int id);

    void destroy(Order order);

    Order select(int id);

    List<Order> select();

    int restore(Order target)throws ProjectException;

    Integer getId(Order target);

    boolean isNotExist(int id);
}
