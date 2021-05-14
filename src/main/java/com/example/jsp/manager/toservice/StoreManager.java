package com.example.jsp.manager.toservice;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.pojo.Store;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface StoreManager {
    int insert(Store store) throws ProjectException;

    void destroy(int id);

    Store select(int id);

    List<Store> select();

    void restore(Store store) throws ProjectException;

    Integer getId(Store store);

    boolean isNotExist(int id);
}
