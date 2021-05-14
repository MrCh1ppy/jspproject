package com.example.jsp.dao;

import com.example.jsp.pojo.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface StoreDao {
    int save(@Param("store") Store store);

    void delete(@Param("id") int id);

    Store selectById(@Param("id") int id);

    List<Store> selectAll();

    void update(@Param("store") Store store);
}