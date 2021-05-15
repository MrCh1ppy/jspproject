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
        Integer id = getId(user);
        if(id==null){
            update(user);
            return 0;
        }
        return id.intValue();
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
    public Integer getId(User target) {
        return userDao.getId(target);
    }

    @Override
    public Integer insert(User targetUser) {
        Integer id = userDao.getId(targetUser);
        if (id != null) {
            targetUser.setId(id);
            userDao.update(targetUser);
            /*没必要专门写一个更新update字段的函数,因为mysql的底层是delete/insert 所以没有意义*/
            return id;
        }
        return save(targetUser);
    }

    @Override
    public boolean isNotExist(int id) {
        return userDao.selectById(id) == null;
    }
}
