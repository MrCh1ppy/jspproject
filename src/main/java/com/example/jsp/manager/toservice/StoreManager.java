package com.example.jsp.manager.toservice;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.pojo.Store;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface StoreManager {
    Integer insert(Store store) throws ProjectException;

    void destroy(Integer id);

    void destroy(Store store);

    Store select(Integer id);

    List<Store> select();

    Integer restore(Store store) throws ProjectException;

    Integer getId(Store store);

    Boolean isNotExist(Integer id);

    Boolean isStore (int userId);
}
