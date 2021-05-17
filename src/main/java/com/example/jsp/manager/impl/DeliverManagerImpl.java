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
        if (id == null) {
            Integer save = save(deliver);
            deliver.setId(save);
            return save;
        }
        deliver.setId(id);
        return deliver.getId();
    }

    @Override
    public List<Deliver> select() {
        return deliverDao.selectAll();
    }

    @Override
    public Deliver select(Integer id) {
        return deliverDao.selectById(id);
    }

    @Override
    public Integer getId(Deliver deliver) {
        return deliverDao.getId(deliver);
    }

    @Override
    public Integer restore(Deliver deliver) throws SonElementNotExistException {
        Integer id = getId(deliver);
        if (id == null) {
            if (userManager.isNotExist(deliver.getLoginUser().getId())) {
                throw new SonElementNotExistException("deliver.user");
            }
            update(deliver);
            return 0;
        }
        deliver.setId(id);
        return deliver.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void destroy(Integer id) {
        destroy(deliverDao.selectById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void destroy(Deliver deliver) {
        userManager.destroy(deliver.getLoginUser().getId());
        delete(deliver.getId());
    }

    @Override
    public Boolean isNotExist(Integer id) {
        return deliverDao.selectById(id) == null;
    }

    @Override
    public void update(Deliver deliver) {
        deliverDao.update(deliver);
    }

    @Override
    public void delete(Integer id) {
        deliverDao.delete(id);
    }

    @Override
    public Boolean isDeliver (int userId) {
        return deliverDao.findIdByLoginUser(userId)!=null;
    }
}
