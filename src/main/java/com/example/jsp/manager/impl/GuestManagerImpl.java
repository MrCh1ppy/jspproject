package com.example.jsp.manager.impl;


import com.example.jsp.commons.exception.SonElementContradictionException;
import com.example.jsp.dao.GuestDao;
import com.example.jsp.manager.UserManager;
import com.example.jsp.pojo.Address;
import com.example.jsp.pojo.Guest;
import com.example.jsp.manager.AddressManager;
import com.example.jsp.manager.GuestManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class GuestManagerImpl implements GuestManager {
    private AddressManager addressManager;
    private UserManager userManager;
    private GuestDao guestDao;

    @Autowired
    public void setAddressService(AddressManager addressManager) {
        this.addressManager = addressManager;
    }

    @Autowired
    public void setUserService(UserManager userManager) {
        this.userManager = userManager;
    }

    @Autowired
    public void setGuestDao(GuestDao guestDao) {
        this.guestDao = guestDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(Guest target) throws SonElementContradictionException {
        var select = userManager.select(target.getLoginUser().getId());
        if (select == null) {
            int save = userManager.save(target.getLoginUser());
            target.getLoginUser().setId(save);
        } else if (!select.equals(target.getLoginUser())) {
            throw new SonElementContradictionException("guest.user");
        }
        return guestDao.save(target);
    }

    @Override
    public void delete(int id) {
        guestDao.delete(id);
        userManager.delete(id);
        List<Address> addresses = addressManager.selectFromG(id);
        for (Address address : addresses) {
            addressManager.delete(address.getId());
        }
    }

    @Override
    public Guest select(int id) {
        return guestDao.selectById(id);
    }

    @Override
    public List<Guest> select() {
        return guestDao.selectAll();
    }

    @Override
    public void update(Guest target) {
        var select = userManager.select(target.getId());
        userManager.updatePre(select.getId(), target.getLoginUser());
        guestDao.update(target);
        addressManager.dropByGuestId(target.getId());
        List<Address> addresses = target.getAddresses();
        for (Address address : addresses) {
            addressManager.save(address);
        }
    }
}
