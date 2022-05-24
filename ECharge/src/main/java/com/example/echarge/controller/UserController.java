package com.example.echarge.controller;

import com.example.echarge.entity.UserEntity;
import com.example.echarge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;
    //获取用户信息 info
    @PostMapping("/info")
    @ResponseBody
    public LinkedHashMap<String, Object> showPersonInfo(Integer uid) {
        System.out.println(uid);
        LinkedHashMap<String, Object> res;
        UserEntity user = userService.showUserInfo(uid);
        res = userService.assembleUserInfo(user);
        System.out.println(res);
        return res;
    }
    //更改用户信息 edit
    //
}
