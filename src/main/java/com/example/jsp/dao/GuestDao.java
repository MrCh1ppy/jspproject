package com.example.jsp.dao;

import com.example.jsp.pojo.Guest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface GuestDao {
    int save(Guest target);

    void delete(int id);

    Guest selectById(int id);

    List<Guest> selectAll();

    void update(Guest target);

}
