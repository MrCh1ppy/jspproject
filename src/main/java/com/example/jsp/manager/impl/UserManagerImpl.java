package com.example.jsp.manager.impl;

import com.example.jsp.dao.UserDao;
import com.example.jsp.manager.todao.UserManagerToDao;
import com.example.jsp.manager.toservice.UserManager;
import com.example.jsp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class UserManagerImpl implements UserManager, UserManagerToDao {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int save(User targetUser) {
        return userDao.save(targetUser);
    }

    @Override
    public void del(int id) {
        userDao.delete(id);
    }

    @Override
    public void destroy(int id) {
        del(id);
    }

    @Override
    public void update(User user) {
        restore(user);
    }

    @Override
    public void restore(User user) {
        userDao.update(user);
    }

    @Override
    public User select(int id) {
        return userDao.selectById(id);
    }

    @Override
    public List<User> select() {
        return userDao.selectAll();
    }

    @Override
    public Integer insert(User targetUser) {
        Integer id = userDao.getId(targetUser);
        if (id != null) {
            targetUser.setId(id);
            userDao.update(targetUser);
            /*没必要专门写一个更新update字段的函数,因为mysql的底层是delete/insert 所以没有意义*/
            return id;
        } else {
            return save(targetUser);
        }
    }

    @Override
    public boolean isNotExist(int id) {
        return userDao.selectById(id) == null;
    }
}
