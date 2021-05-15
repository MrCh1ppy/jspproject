package com.example.jsp.dao;

import com.example.jsp.pojo.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
public interface AddressDao {
    int save(@Param("target") Address target);

    void delete(@Param("id") int id);

    Address selectById(@Param("id") int id);

    List<Address> selectAll();

    void update(@Param("target") Address target);

    List<Address> selectByGuestId(@Param("id") int id);

    void dropByGuestId(@Param("id") int id);

    Integer getId(@Param("target") Address address);
}
