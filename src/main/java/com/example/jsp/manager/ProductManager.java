package com.example.jsp.manager;

import com.example.jsp.commons.exception.SonElementContradictionException;
import com.example.jsp.pojo.Product;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface ProductManager {
    int save(Product target) throws SonElementContradictionException;

    void delete(int id);

    Product select(int id);

    List<Product> select();

    void update(Product target) throws SonElementContradictionException;
}
