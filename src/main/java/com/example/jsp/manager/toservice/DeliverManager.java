package com.example.jsp.manager.toservice;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.pojo.Deliver;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface DeliverManager {
    Integer insert(Deliver deliver) throws ProjectException;

    List<Deliver> select();

    Deliver select(Integer id);

    Integer getId(Deliver deliver);

    Integer restore(Deliver deliver) throws ProjectException;

    void destroy(Integer id);

    void destroy(Deliver deliver);

    Boolean isNotExist(Integer id);
}
