package com.example.jsp.manager;

import com.example.jsp.commons.exception.SonElementContradictionException;
import com.example.jsp.pojo.Store;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface StoreManager {
    int save(Store store) throws SonElementContradictionException;

    void delete(int id);

    Store select(int id);

    List<Store> select();

    void update(Store store);

    int savePre(Store store) throws SonElementContradictionException;

    int updatePre(Store store) throws SonElementContradictionException;
}
