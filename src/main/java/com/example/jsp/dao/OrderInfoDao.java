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
    int save(@Param("target") OrderInfo target);

    void delete(@Param("target") int id);

    OrderInfo selectById(@Param("target") int id);

    void update(@Param("target") OrderInfo target);

    List<OrderInfo> selectByOrderId(@Param("id") int id);

    Integer getId(@Param("target") OrderInfo orderInfo);

    void deleteByOrder(@Param("id") int id);
}
