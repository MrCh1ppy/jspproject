package com.example.jsp.manager;

import com.example.jsp.commons.exception.SonElementContradictionException;
import com.example.jsp.pojo.User;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface UserManager {
    int save(User user);

    void update(User user);

    void delete(int id);

    List<User> select();

    User select(int id);

    int updatePre(int id, User user);

    int savePre(User user) throws SonElementContradictionException;
}
