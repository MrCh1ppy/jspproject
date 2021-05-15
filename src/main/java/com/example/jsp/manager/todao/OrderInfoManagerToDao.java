package com.example.jsp.manager.todao;

import com.example.jsp.pojo.OrderInfo;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface OrderInfoManagerToDao {
    int save(OrderInfo target);

    void delete(int id);

    OrderInfo select(int id);

    void update(OrderInfo target);

    List<OrderInfo> selectByOrderId(int id);

    Integer getId(OrderInfo orderInfo);

    boolean isNotExist(int id);

    void deleteByOrderId(int id);
}
