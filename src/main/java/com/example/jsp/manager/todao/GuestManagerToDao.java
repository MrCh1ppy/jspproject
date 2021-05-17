package com.example.jsp.manager.todao;

import com.example.jsp.pojo.Guest;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface GuestManagerToDao {
    Integer save(Guest guest);

    void delete(Integer id);

    void update(Guest guest);

    Guest select(Integer id);

    List<Guest> select();

    Boolean isGuest(int userId);
}
