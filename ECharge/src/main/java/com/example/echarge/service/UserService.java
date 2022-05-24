package com.example.echarge.service;

import com.example.echarge.dao.UserDao;
import com.example.echarge.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public UserEntity getUserByToken(String token) {
        return userDao.findByToken(token);
    }
    public UserEntity showUserInfo(int uid){
        return userDao.findByUid(uid);
    }

    public LinkedHashMap<String, Object> assembleUserInfo(UserEntity user) {
        LinkedHashMap<String, Object> res = new LinkedHashMap<String, Object>(0);
        res.put("name", user.getName());
        res.put("gender",user.getGender());
        res.put("phone",user.getPhoneNumber());
        res.put("iconUrl",user.getIconUrl());
        return res;
    }

    public void updateUser(UserEntity user) {
        userDao.saveAndFlush(user);
    }

}
