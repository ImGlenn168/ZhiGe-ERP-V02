package com.java.zhigeerpv02.controller;


import com.java.zhigeerpv02.entity.User;
import com.java.zhigeerpv02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public User findUser(@RequestBody User user) {
        try {
            user = userService.loginUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @PostMapping("/user/register")
    public int addUser(@RequestBody User user) {
        int i;
        try {
            i = userService.addUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    @PostMapping("/user/check")
    public User checkUser(@RequestBody User user) {
        try {
            user = userService.findByUserName(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
