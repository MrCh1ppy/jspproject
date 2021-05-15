package com.example.jsp.manager.toservice;

import com.example.jsp.pojo.Address;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface AddressManager {
    List<Address> select();

    Address select(int id);

    int insert(Address address);

    void destroy(int id);

    void destroy(Address address);

    int restore(Address address);

    List<Address> selectByGuestId(int id);

    void dropByGuestId(int id);

    boolean isNotExist(int id);
}
