package com.example.jsp.manager.toservice;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.pojo.Product;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface ProductManager {
    int insert(Product target) throws ProjectException;

    void destroy(int id);

    void destroy(Product target);

    Product select(int id);

    List<Product> select();

    int restore(Product target) throws ProjectException;

    Integer getId(Product product);

    boolean isNotExist(int id);
}
