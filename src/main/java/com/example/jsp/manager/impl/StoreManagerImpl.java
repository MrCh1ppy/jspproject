package com.example.jsp.manager.impl;

import com.example.jsp.commons.exception.manager.SonElementNotExistException;
import com.example.jsp.dao.StoreDao;
import com.example.jsp.manager.todao.StoreManagerToDao;
import com.example.jsp.manager.toservice.StoreManager;
import com.example.jsp.manager.toservice.UserManager;
import com.example.jsp.pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class StoreManagerImpl implements StoreManagerToDao, StoreManager {
    private UserManager userManager;
    private StoreDao storeDao;

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @Autowired
    public void setStoreDao(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @Override
    public int save(Store store) {
        return storeDao.save(store);
    }

    @Override
    public void delete(int id) {
        storeDao.delete(id);
    }

    @Override
    public int insert(Store store) throws SonElementNotExistException {
        if (userManager.isNotExist(store.getUser().getId())) {
            throw new SonElementNotExistException("Store.user");
        }

        Integer id = storeDao.getId(store);
        if (id == null) {
            int save = save(store);
            store.setId(save);
            return save;
        }
        return id.intValue();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void destroy(int id) {
        destroy(select(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void destroy(Store store) {
        userManager.destroy(store.getId());
        delete(store.getId());
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
    public int restore(Store store) throws SonElementNotExistException {
        Integer id = getId(store);
        if (id == null) {
            if (userManager.isNotExist(store.getUser().getId())) {
                throw new SonElementNotExistException();
            }
            storeDao.update(store);
            return 0;
        }
        return id;
    }

    @Override
    public void update(Store store) {
        storeDao.update(store);
    }

    @Override
    public Integer getId(Store store) {
        return storeDao.getId(store);
    }

    @Override
    public boolean isNotExist(int id) {
        return storeDao.selectById(id) == null;
    }
}
