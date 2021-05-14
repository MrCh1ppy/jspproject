package com.example.jsp.dao;

import com.example.jsp.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface ProductDao {
    int save(@Param("target") Product target);

    void delete(@Param("id") int id);

    Product selectById(@Param("id") int id);

    List<Product> selectAll();

    void update(@Param("target") Product target);
}
