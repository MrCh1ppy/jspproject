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
    public void destroy(User user) {
        del(user.getId());
    }

    @Override
    public void update(User user) {
        restore(user);
    }

    @Override
    public int restore(User user) {
        User temp = getId(user);
        if (temp == null) {
            update(user);
            return 0;
        }
        user.setId(temp.getId());
        return temp.getId();
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
    public User getId(User target) {
        return userDao.getId(target);
    }

    @Override
    public Integer insert(User targetUser) {
        User tempUser = userDao.getId(targetUser);
        if (tempUser != null) {
            targetUser.setId(tempUser.getId());
            userDao.update(targetUser);
            /*没必要专门写一个更新update字段的函数,因为mysql的底层是delete/insert 所以没有意义*/
            return tempUser.getId();
        }
        targetUser.setId(save(targetUser));
        return targetUser.getId();
    }

    @Override
    public boolean isNotExist(int id) {
        return userDao.selectById(id) == null;
    }
}
