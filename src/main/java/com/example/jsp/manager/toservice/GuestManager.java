package com.example.jsp.manager.toservice;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.pojo.Guest;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface GuestManager {
    Integer insert(Guest target) throws ProjectException;

    void destroy(Integer id);

    void destroy(Guest guest);

    Guest select(Integer id);

    List<Guest> select();

    Integer restore(Guest target) throws ProjectException;

    Integer getId(Guest target);

    Boolean isNotExist(Integer id);
}
