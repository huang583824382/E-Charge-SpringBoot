package com.example.echarge.controller;

import com.example.echarge.entity.UserEntity;
import com.example.echarge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;
    //获取用户信息 info
    @RequestMapping("/info")
    public LinkedHashMap<String, Object> showPersonInfo(int uid) {
        LinkedHashMap<String, Object> res;
        UserEntity user = userService.showUserInfo(uid);
        res = userService.assembleUserInfo(user);
        return res;
    }
    //更改用户信息 edit
    //
}
