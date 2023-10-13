package com.java.zhigeerpv02.service;

import com.java.zhigeerpv02.dao.user.UserDao;
import com.java.zhigeerpv02.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User loginUser(User user) {
        return userDao.loginUser(user.getUname(), user.getPassword());
    }


    public User findByUserName(User user) {
        return userDao.findByUserName(user.getUname());
    }

    public int addUser(User user) {
        return userDao.addUser(user);
    }


}
