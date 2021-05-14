package com.example.jsp.manager.impl;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.commons.exception.manager.SonElementNotExistException;
import com.example.jsp.dao.DeliverDao;
import com.example.jsp.manager.todao.DeliverManagerToDao;
import com.example.jsp.manager.toservice.DeliverManager;
import com.example.jsp.manager.toservice.UserManager;
import com.example.jsp.pojo.Deliver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author 橙鼠鼠
 */
@Service
public class DeliverManagerImpl implements DeliverManagerToDao, DeliverManager {
    private UserManager userManager;
    private DeliverDao deliverDao;

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @Autowired
    public void setDeliverDao(DeliverDao deliverDao) {
        this.deliverDao = deliverDao;
    }

    @Override
    public Integer save(Deliver deliver) {
        return deliverDao.save(deliver);
    }

    @Override
    public Integer insert(Deliver deliver) throws ProjectException {
        if (userManager.isNotExist(deliver.getLoginUser().getId())) {
            throw new SonElementNotExistException("deliver.User");
        }
        Integer id = getId(deliver);
        return Objects.requireNonNullElseGet(id, () -> deliverDao.save(deliver));
    }

    @Override
    public List<Deliver> select() {
        return deliverDao.selectAll();
    }

    @Override
    public Deliver select(int id) {
        return deliverDao.selectById(id);
    }

    @Override
    public Integer getId(Deliver deliver) {
        return deliverDao.getId(deliver);
    }

    @Override
    public void restore(Deliver deliver) throws SonElementNotExistException {
        if (userManager.isNotExist(deliver.getLoginUser().getId())) {
            throw new SonElementNotExistException("deliver.user");
        }
        update(deliver);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void destroy(int id) {
        userManager.destroy(deliverDao.selectById(id).getLoginUser().getId());
        delete(id);
    }

    @Override
    public boolean isNotExist(int id) {
        return deliverDao.selectById(id)==null;
    }

    @Override
    public void update(Deliver deliver) {
        deliverDao.update(deliver);
    }

    @Override
    public void delete(int id) {
        deliverDao.delete(id);
    }
}
