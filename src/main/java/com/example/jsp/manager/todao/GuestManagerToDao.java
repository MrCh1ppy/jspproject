package com.example.jsp.manager.todao;

import com.example.jsp.pojo.Guest;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface GuestManagerToDao {
    int save(Guest guest);

    void delete(int id);

    void update(Guest guest);

    Guest select(int id);

    List<Guest> select();
}
