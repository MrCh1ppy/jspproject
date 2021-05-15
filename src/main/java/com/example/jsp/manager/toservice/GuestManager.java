package com.example.jsp.manager.toservice;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.pojo.Guest;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface GuestManager {
    int insert(Guest target) throws ProjectException;

    void destroy(int id);

    void destroy(Guest guest);

    Guest select(int id);

    List<Guest> select();

    int restore(Guest target) throws ProjectException;

    Integer getId(Guest target);

    boolean isNotExist(int id);
}
