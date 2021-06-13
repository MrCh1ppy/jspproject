package com.example.jsp.service;

import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.pojo.Order;
import com.example.jsp.pojo.ProductPackage;

public interface OrderService {
    void create (Order order) throws ProjectException;

    void delete (Order order) throws ProjectException;

    void delete (Integer id) throws ProjectException;

    void restore(Order order) throws ProjectException;

    Order select(Integer orderId) throws ProjectException;

    OrderService addProduct(Order order , ProductPackage productPackage) throws ProjectException;

}
