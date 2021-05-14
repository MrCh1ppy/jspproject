package com.example.jsp.manager.impl;

import com.example.jsp.commons.exception.SonElementContradictionException;
import com.example.jsp.dao.UserDao;
import com.example.jsp.pojo.User;
import com.example.jsp.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class UserManagerImpl implements UserManager {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int save(User user) {
        userDao.save(user);
        return user.getId();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public List<User> select() {
        return userDao.selectAll();
    }

    @Override
    public User select(int id) {
        return userDao.selectById(id);
    }

    @Override
    public int updatePre(int id, User user) {
        var user1 = userDao.selectById(id);
        int res;
        if (user1 == null) {
            res = this.save(user);
        } else if (id == user.getId()) {
            this.update(user);
            res = id;
        } else {
            this.delete(id);
            res = this.save(user);
        }
        return res;
    }

    @Override
    public int savePre(User user) throws SonElementContradictionException {
        var user1 = userDao.selectById(user.getId());
        if (user1 != null && !user.equals(user1)) {
            throw new SonElementContradictionException("XXX.user");
        }
        return userDao.save(user1);
    }

}
