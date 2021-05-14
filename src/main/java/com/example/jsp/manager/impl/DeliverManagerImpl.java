package com.example.jsp.manager.impl;

import com.example.jsp.commons.exception.SonElementContradictionException;
import com.example.jsp.dao.DeliverDao;
import com.example.jsp.manager.UserManager;
import com.example.jsp.pojo.Deliver;
import com.example.jsp.pojo.User;
import com.example.jsp.manager.DeliverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class DeliverManagerImpl implements DeliverManager {
    private DeliverDao deliverDao;
    private UserManager userManager;

    @Autowired
    private void setDeliverDao(DeliverDao deliverDao) {
        this.deliverDao = deliverDao;
    }

    @Autowired
    public void setUserService(UserManager userManager) {
        this.userManager = userManager;
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
    @Transactional(rollbackFor = Exception.class)
    public int save(Deliver deliver) throws SonElementContradictionException {
        var select = userManager.select(deliver.getLoginUser().getId());
        if (select == null) {
            int save = userManager.save(deliver.getLoginUser());
            deliver.getLoginUser().setId(save);
        } else if (!deliver.getLoginUser().equals(select)) {
            throw new SonElementContradictionException("deliver.son");
        }
        return deliverDao.save(deliver);
    }

    @Override
    @Deprecated
    /**@deprecated 减少使用级联更新
     * @date 2021.5.13
     * */
    public int save(Deliver deliver, User user) {
        if (userManager.select(user.getId()) == null) {
            userManager.save(user);
        } else {
            userManager.update(user);
        }
        deliver.setId(user.getId());
        deliverDao.save(deliver);
        return deliver.getId();
    }

    @Override
    public void delete(int id) {
        deliverDao.delete(id);
        userManager.delete(id);
    }

    @Override
    public void update(Deliver deliver) {
        var select = userManager.select(deliver.getId());
        userManager.updatePre(select.getId(), deliver.getLoginUser());
        deliverDao.update(deliver);
    }
}
