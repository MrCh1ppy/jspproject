package com.example.jsp.manager;

import com.example.jsp.commons.exception.SonElementContradictionException;
import com.example.jsp.pojo.Guest;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface GuestManager {
    int save(Guest target) throws SonElementContradictionException;

    void delete(int id);

    Guest select(int id);

    List<Guest> select();

    void update(Guest target);
}
