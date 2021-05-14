package com.example.jsp.manager.impl;

import com.example.jsp.dao.AddressDao;
import com.example.jsp.pojo.Address;
import com.example.jsp.manager.AddressManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class AddressManagerImpl implements AddressManager {
    private AddressDao addressDao;

    @Autowired
    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public int save(Address address) {
        addressDao.save(address);
        return address.getId();
    }

    @Override
    public void delete(int id) {
        addressDao.delete(id);
    }

    @Override
    public Address select(int id) {
        return addressDao.selectById(id);
    }

    @Override
    public List<Address> select() {
        return addressDao.selectAll();
    }

    @Override
    public List<Address> selectFromG(int id) {
        return addressDao.selectByGuestId(id);
    }

    @Override
    public void update(Address address) {
        addressDao.update(address);
    }

    @Override
    public void dropByGuestId(int id) {
        addressDao.dropByGuestId(id);
    }
}
