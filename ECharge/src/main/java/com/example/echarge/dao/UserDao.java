package com.example.echarge.dao;

import com.example.echarge.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, String> {
    UserEntity findByWxId(String wxId);
    UserEntity findByToken(String sessionKey);
    UserEntity findByName(String nickName);
    UserEntity findByPhoneNumber(String phoneNumber);
}
