package com.example.jsp.manager.todao;

import com.example.jsp.pojo.User;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface UserManagerToDao {
    int save(User targetUser);

    void del(int id);

    void update(User user);

    User select(int id);

    List<User> select();

    Integer getId(User target);
}
