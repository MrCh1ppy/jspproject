package com.example.jsp.manager.impl;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.commons.exception.manager.SonElementNotExistException;
import com.example.jsp.dao.GuestDao;
import com.example.jsp.manager.todao.GuestManagerToDao;
import com.example.jsp.manager.toservice.AddressManager;
import com.example.jsp.manager.toservice.GuestManager;
import com.example.jsp.manager.toservice.UserManager;
import com.example.jsp.pojo.Address;
import com.example.jsp.pojo.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class GuestManagerImpl implements GuestManagerToDao, GuestManager {
    private GuestDao guestDao;
    private UserManager userManager;
    private AddressManager addressManager;

    @Autowired
    public void setGuestDao(GuestDao guestDao) {
        this.guestDao = guestDao;
    }

    @Autowired
    public void setAddressManager(AddressManager addressManager) {
        this.addressManager = addressManager;
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public Integer save(Guest guest) {
        guestDao.save(guest);
        return guest.getId();
    }

    @Override
    public void delete(Integer id) {
        guestDao.delete(id);
    }

    @Override
    public void update(Guest guest) {
        guestDao.update(guest);
    }

    /**
     * @apiNote :如果寻找到有相同内容的数据,将停止插入对应的子元素转而使用数据表中原先记录的数据,
     * 即如果发现插入的id与原先的不一样,需要使用update而不是insert
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insert(Guest target) throws ProjectException {
        if (userManager.isNotExist(target.getLoginUser().getId())) {
            throw new SonElementNotExistException("Guest.user");
        }

        Integer id = guestDao.getId(target);
        if (id == null) {
            int save = save(target);
            target.setId(save);
            for (Address address : target.getAddresses()) {
                int i = addressManager.insert(address);
                address.setId(i);
            }
            return save;
        }
        target.setId(id);
        return target.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void destroy(Integer id) {
        destroy(select(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void destroy(Guest guest) {
        userManager.destroy(guest.getLoginUser().getId());
        addressManager.dropByGuestId(guest.getId());
        delete(guest.getId());
    }

    @Override
    public Guest select(Integer id) {
        return guestDao.selectById(id);
    }

    @Override
    public List<Guest> select() {
        return guestDao.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    /**@apiNote :对于地址的更新,直接使用完全删除与完全添加;
     * */
    public Integer restore(Guest target) throws ProjectException {
        Integer id = getId(target);
        if (id == null) {
            if (userManager.isNotExist(target.getLoginUser().getId())) {
                throw new SonElementNotExistException();
            }
            addressManager.dropByGuestId(target.getId());
            for (Address address : target.getAddresses()) {
                int i = addressManager.insert(address);
                address.setId(i);
            }
            guestDao.update(target);
            return 0;
        }
        target.setId(id);
        return target.getId();
    }

    @Override
    public Integer getId(Guest target) {
        return guestDao.getId(target);
    }

    @Override
    public Boolean isNotExist(Integer id) {
        return select(id) == null;
    }
}
