package com.example.echarge.controller;

import com.example.echarge.entity.CommodityEntity;
import com.example.echarge.entity.UserEntity;
import com.example.echarge.service.ListService;
import com.example.echarge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ListService listService;

    @PostMapping("/goods")
    @ResponseBody
    public List<CommodityEntity> getPersonGoods(String token, int uid, int size){
        UserEntity user = userService.getUserByToken(token);
        if(user!=null){
            List<CommodityEntity> tmp = listService.getUserList(uid,0, size);
            System.out.println("get user goods"+tmp.size());
            return tmp;
        }
        return null;
    }

    @PostMapping("/tasks")
    @ResponseBody
    public List<CommodityEntity> getPersonTasks(String token, int uid, int size){
        UserEntity user = userService.getUserByToken(token);
        if(user!=null){
            List<CommodityEntity> tmp = listService.getUserList(uid,1, size);
            System.out.println("get user goods"+tmp.size());
            return tmp;
        }
        return null;
    }

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
    @PostMapping("editName")
    @ResponseBody
    public LinkedHashMap<String, Object> editName(String token, String name){
        UserEntity user = userService.getUserByToken(token);
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        if(userService.checkName(name)){
            user.setName(name);
            userService.updateUser(user);
            res.put("name", name);
            res.put("code", "success");
        }
        else{
            res.put("code", "fail");
        }
        return res;
    }

    @PostMapping("editGender")
    @ResponseBody
    public LinkedHashMap<String, Object> editGender(String token, int gender){
        UserEntity user = userService.getUserByToken(token);
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        user.setGender(gender);
        userService.updateUser(user);
        res.put("gender", gender);
        res.put("code", "success");
        return res;
    }

    @PostMapping("editPhone")
    @ResponseBody
    public LinkedHashMap<String, Object> editPhone(String token, String phone){
        UserEntity user = userService.getUserByToken(token);
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        user.setPhoneNumber(phone);
        userService.updateUser(user);
        res.put("phone", phone);
        res.put("code", "success");
        return res;
    }

    @PostMapping("editAvatar")
    @ResponseBody
    public LinkedHashMap<String, Object> editAvatar(String token, MultipartFile img){
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        UserEntity user = userService.getUserByToken(token);
        if(userService.editAvatar(user, img)){
            res.put("code", "success");
        }
        return res;

    }
    //
}
