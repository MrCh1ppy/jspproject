package com.example.jsp.manager.impl;

import com.example.jsp.commons.exception.SonElementContradictionException;
import com.example.jsp.dao.StoreDao;
import com.example.jsp.manager.UserManager;
import com.example.jsp.pojo.Store;
import com.example.jsp.manager.StoreManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class StoreManagerImpl implements StoreManager {
    private StoreDao storeDao;
    private UserManager userManager;

    @Autowired
    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @Autowired
    public void setUserService(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(Store store) throws SonElementContradictionException {
        var user = userManager.select(store.getUser().getId());
        if (user == null) {
            int save = userManager.save(store.getUser());
            store.getUser().setId(save);
        } else if (!user.equals(store.getUser())) {
            throw new SonElementContradictionException("Store.user");
        }
        return storeDao.save(store);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(int id) {
        var user = userManager.select(id);
        userManager.delete(user.getId());
        storeDao.delete(id);
    }

    @Override
    public Store select(int id) {
        return storeDao.selectById(id);
    }

    @Override
    public List<Store> select() {
        return storeDao.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Store store) {
        var store1 = storeDao.selectById(store.getId());
        int user = userManager.updatePre(store1.getId(), store.getUser());
        store.setUser(userManager.select(user));
        storeDao.update(store);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int savePre(Store store) throws SonElementContradictionException {
        var select = select(store.getId());
        if (select != null && !store.equals(select)) {
            throw new SonElementContradictionException();
        }
        return save(store);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePre(Store store) throws SonElementContradictionException {
        var select = select(store.getId());
        if (select == null) {
            return this.save(store);
        } else if (select.getId() != store.getId()) {
            delete(select.getId());
            return this.save(store);
        } else {
            this.update(store);
            return store.getId();
        }
    }
}
