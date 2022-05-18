package com.example.echarge.service;

import com.example.echarge.dao.UserDao;
import com.example.echarge.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public UserEntity getUserByToken(String token) {
        return userDao.findByToken(token);
    }
}
