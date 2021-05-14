package com.example.jsp.manager;

import com.example.jsp.pojo.Address;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface AddressManager {
    int save(Address address);

    void delete(int id);

    Address select(int id);

    List<Address> select();

    List<Address> selectFromG(int id);

    void update(Address address);

    void dropByGuestId(int id);
}
