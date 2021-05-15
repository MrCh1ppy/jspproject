package com.example.jsp.manager.todao;

import com.example.jsp.pojo.Deliver;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface DeliverManagerToDao {
    Integer save(Deliver deliver);

    List<Deliver> select();

    Deliver select(int id);

    Integer getId(Deliver deliver);

    void update(Deliver deliver);

    void delete(int id);
}
