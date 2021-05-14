package com.example.jsp.manager;

import com.example.jsp.commons.exception.SonElementContradictionException;
import com.example.jsp.pojo.Deliver;
import com.example.jsp.pojo.User;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface DeliverManager {
    List<Deliver> select();

    Deliver select(int id);

    int save(Deliver deliver) throws SonElementContradictionException;

    int save(Deliver deliver, User user);

    void delete(int id);

    void update(Deliver deliver);
}
