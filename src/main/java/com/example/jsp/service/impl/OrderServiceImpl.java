package com.example.jsp.service.impl;


import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.oldexception.manager.SonElementNotExistExceptionOld;
import com.example.jsp.manager.toservice.OrderManager;
import com.example.jsp.pojo.Order;
import com.example.jsp.pojo.ProductPackage;
import com.example.jsp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderManager orderManager;

    @Autowired
    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    @Override
    public void create(Order order) throws ProjectException {
        try{
            orderManager.insert(order);
        } catch (SonElementNotExistExceptionOld sonElementNotExistExceptionOld){
            throw new ProjectException(sonElementNotExistExceptionOld.toString(), 305);
        }
    }

    @Override
    public void delete(Order order) throws ProjectException {
        orderManager.destroy(order);
    }

    @Override
    public void delete(Integer id) throws ProjectException {
        orderManager.destroy(id);
    }

    @Override
    public void restore(Order order) throws ProjectException {
        try {
            orderManager.restore(order);
        } catch (SonElementNotExistExceptionOld e){
            throw new ProjectException(e.toString(), 302);
        }
    }

    @Override
    public Order select(Integer orderId) throws ProjectException {
        return orderManager.select(orderId);
    }

    @Override
    public OrderService addProduct(Order order, ProductPackage productPackage) throws ProjectException {
        order.getProductPackages().add(productPackage);
        restore(order);
        return this;
    }
}
