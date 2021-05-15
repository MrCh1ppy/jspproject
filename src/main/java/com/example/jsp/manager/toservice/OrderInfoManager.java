package com.example.jsp.manager.toservice;

import com.example.jsp.pojo.OrderInfo;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface OrderInfoManager {
    int insert(OrderInfo target);

    void destroy(int id);

    void destroy(OrderInfo orderInfo);

    OrderInfo select(int id);

    void deleteByOrderId(int id);

    int restore(OrderInfo target);

    List<OrderInfo> selectByOrderId(int id);

    Integer getId(OrderInfo orderInfo);
}
