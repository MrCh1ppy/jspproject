package com.example.jsp.manager.toservice;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.pojo.Product;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface ProductManager {
    Integer insert(Product target) throws ProjectException;

    void destroy(Integer id);

    void destroy(Product target);

    Product select(Integer id);

    List<Product> select();

    Integer restore(Product target) throws ProjectException;

    Integer getId(Product product);

    Boolean isNotExist(Integer id);
}
