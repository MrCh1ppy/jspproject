package com.example.jsp.manager.toservice;

import com.example.jsp.pojo.User;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface UserManager {

    void destroy(int id);

    void restore(User user);

    User select(int id);

    List<User> select();

    Integer insert(User targetUser);

    boolean isNotExist(int id);
}
