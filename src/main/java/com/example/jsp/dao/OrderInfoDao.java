package com.example.jsp.dao;

import com.example.jsp.pojo.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface OrderInfoDao {
    int save(OrderInfo target);

    void delete(int id);

    void selectById(int id);

    void update(OrderInfo target);

    List<OrderInfo> selectByOrderId(@Param("id") int id);
}
