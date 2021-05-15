package com.example.jsp.manager.todao;

import com.example.jsp.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 橙鼠鼠
 */

public interface ProductManagerToDao {
    int save(@Param("target") Product target);

    void delete(@Param("id") int id);

    Product select(@Param("id") int id);

    List<Product> select();

    void update(@Param("target") Product target);

    Integer getId(@Param("target") Product product);
}
