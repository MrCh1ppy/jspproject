package com.example.jsp.manager.todao;

import com.example.jsp.pojo.Address;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface AddressManagerToDao {
    List<Address> select();

    Address select(int id);

    int save(Address address);

    void delete(int id);

    void update(Address address);

    List<Address> selectByGuestId(int id);

    void dropByGuestId(int id);
}
