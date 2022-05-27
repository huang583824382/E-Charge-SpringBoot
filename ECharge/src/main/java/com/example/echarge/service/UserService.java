package com.example.echarge.service;

import com.example.echarge.dao.UserDao;
import com.example.echarge.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.echarge.util.saveImg;

import java.io.IOException;
import java.util.LinkedHashMap;

@Service
public class UserService {
    @Value("${upload-url}") private String uploadUrl;
    @Value("${upload-path}") private String uploadPath;
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
        if(user!=null){
            res.put("name", user.getName());
            res.put("gender",user.getGender());
            res.put("phone",user.getPhoneNumber());
            res.put("iconUrl",user.getIconUrl());
        }

        return res;
    }

    public void updateUser(UserEntity user) {
        userDao.saveAndFlush(user);
    }
    public boolean checkName(String name){
        UserEntity user = userDao.findByName(name);
        if(user != null) {
            return false;
        }
        else{
            return true;
        }
    }
    public boolean editAvatar(UserEntity user, MultipartFile img){
        String url = null;
        try {
            url = uploadUrl+saveImg.saveImage(img, uploadPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("url "+url);
        user.setIconUrl(url);
        userDao.saveAndFlush(user);
        return true;
    }

}
