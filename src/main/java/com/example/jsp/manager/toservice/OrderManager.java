package com.example.jsp.manager.toservice;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.pojo.Order;
import com.example.jsp.pojo.OrderInfo;
import com.example.jsp.pojo.Product;

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

    OrderManager addProduct(Order target, Product product,int num)throws ProjectException;

    OrderManager addProduct(Order target, int productId,int num)throws ProjectException;

    OrderManager linkedInsert(Order target)throws ProjectException;

    OrderManager linkedRestore(Order order)throws ProjectException;

    OrderManager linkedDestroy(Order target);

    OrderManager linkedDestroy(int orderId);

    OrderManager addOrderInfo(Order target, OrderInfo orderInfo);

    OrderManager addOrderInfo(Order target,int OrderInfoId)throws ProjectException;
}
