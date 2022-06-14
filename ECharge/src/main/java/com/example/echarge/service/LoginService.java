/**
 * 文 件 名 : LoginService.java
 * 描 述 : 登录和注册相关服务
 * 最后修改时间 : 2022-5-17 15:26 am
 */

package com.example.echarge.service;

import com.example.echarge.dao.UserDao;
import com.example.echarge.entity.UserEntity;
import com.example.echarge.util.saveImg;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.UUID;


@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    private final String appID = "wx24d42abf42402eef";
    private final String appSecret = "2f5a0937d16321a23c7e95b0bfa78382";
    private RestTemplate restTemplate = new RestTemplate();
    public LinkedHashMap<String, Object> getUserInfo(String code)
    {
        System.out.println(code);
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
                + this.appID + "&secret="
                + this.appSecret + "&js_code="
                + code
                + "&grant_type=authorization_code";
        String resStr = restTemplate.getForObject(url, String.class);
        System.out.println(resStr);
        assert resStr != null;
        JSONParser json = new JSONParser(resStr);
        LinkedHashMap<String, Object> res;
        try {
            res = json.parseObject();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String session = res.get("session_key").toString();
        String openid = res.get("openid").toString();
        System.out.println("res" + session+" "+openid);
//        数据库中查找用户
        UserEntity user = userDao.findByWxId(openid);
        if(user == null){
            // 加入用户字段
            user = new UserEntity();
            user.setWxId(openid);
            user.setToken(session);
//            user.setName("temp_user");
//            System.out.println(user.getWxId());
//            user.setName(UUID.randomUUID().toString().replace("-", ""));
            userDao.saveAndFlush(user);
            res.put("option_code", 0); //登录失败
            System.out.println("no user");
        }
        else if(user.getName() == null){
            res.put("option_code", 0); //登录失败
            System.out.println("no user");
        }
        else{
            user.setToken(session);
            userDao.saveAndFlush(user);
            res.put("option_code", 1);
            res.put("uid", user.getUid());
            System.out.println("has user");
        }
        return res;
    }

    /**
     * 组装返回信息和uid
     * @param info 图片流
     * @param uid 用户uid
     * @return {"info": info, "uid", uid} 组装好的返回值
     */
    private LinkedHashMap<String, Object> assembleRetVal(String info, Integer uid) {
        LinkedHashMap<String, Object> res = new LinkedHashMap<String, Object>(0);
        res.put("info", info);
        res.put("uid", uid);
        return res;
    }
    private LinkedHashMap<String, Object> assembleRetVal(String info, Integer uid, String name, String avatarUrl) {
        LinkedHashMap<String, Object> res = new LinkedHashMap<String, Object>(0);
        res.put("info", info);
        res.put("uid", uid);
        res.put("name", name);
        res.put("avatar", avatarUrl);
        return res;
    }

    /**
     * 判断注册信息，返回判断结果
     * @param session_key 用户token
     * @param nickName 用户昵称
     * @param phoneNumber 用户电话号码
     * @return {"info": info, "uid", null} 组装好的返回值
     */
    public LinkedHashMap<String, Object> checkRegInfo(String session_key, String nickName, String phoneNumber) {
        // 数据库中查找session key
        UserEntity user = userDao.findByToken(session_key);
        if(user == null) {
            return assembleRetVal("verification error", null);
        }
        // 数据库中查找是否有重复的昵称和手机号
        else {
            user = userDao.findByName(nickName);
            if(user != null) {
                return assembleRetVal("same nickname", null);
            }
            user = userDao.findByPhoneNumber(phoneNumber);
            if(user != null) {
                return  assembleRetVal("same phone number", null);
            }
        }
        return null;
    }

    @Value("${upload-url}") private String uploadUrl;
    @Value("${upload-path}") private String uploadPath;

    /**
     * 添加用户信息
     * @param session_key 用户token
     * @param avatar_url 用户头像url
     * @param nickName 用户昵称
     * @param phoneNumber 用户手机号
     * @param gender 用户性别
     * @param image 用户头像图片流
     * @return {"info": info, "uid", uid} 组装好的返回值
     */
    public LinkedHashMap<String, Object> addUser(String session_key, String avatar_url, String nickName,
                                                 String phoneNumber, int gender, MultipartFile image) throws IOException {
        // 保存头像
        String f_url;
        if(image != null) {
            String fid = UUID.randomUUID().toString().replace("-", "");
            f_url = uploadUrl + fid + ".jpg";
            String f_path = uploadPath + fid + ".jpg";
            //System.out.println(fid);
            // 创建目录
            File Folder = new File(uploadPath);
            if(!Folder.exists() || !Folder.isDirectory()) Folder.mkdirs();
            // 保存头像
            saveImg.saveOneImg(image, f_path);
        }
        else {
            f_url = avatar_url;
        }
        // 更新用户
        UserEntity user = userDao.findByToken(session_key); // 保证有对应用户记录
        user.setName(nickName);
        user.setPhoneNumber(phoneNumber);
        user.setGender(gender);
        user.setIconUrl(f_url);
        user.setCredit(100);
        user.setBalance(100.0);
        userDao.saveAndFlush(user);

        return assembleRetVal("success", user.getUid(), user.getName(), user.getIconUrl());
    }
}
