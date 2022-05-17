/**
 * 文 件 名 : LoginController.java
 * 描 述 : 登录和注册过程的控制器
 * 最后修改时间 : 2022-5-17 01:53 am
 */

package com.example.echarge.controller;

import com.example.echarge.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.UUID;

@RestController
@RequestMapping("/start")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("login") //接收客户端传来的wx通行证，与wx服务器通信，返回登录结果
    @ResponseBody
    public Object loginin(HttpServletRequest Request, HttpServletResponse response, String code){
        System.out.println("recieved "+code);
//        请求微信服务器
//        获取user信息回复
        LinkedHashMap<String, Object> data = loginService.getUserInfo(code);
        return data;
    }

    /**
     * 接收有上传头像的注册数据，存储数据库并绑定，返回处理结果和uid
     * @param img 头像图片流
     * @param session_key 当前会话密钥
     * @param avatar_url 头像url
     * @param nickName 用户昵称
     * @param phoneNumber 用户电话号码
     * @param gender 用户性别
     * @return {info: 处理结果, uid: 用户uid}
     */
    @PostMapping("register/ava") //
    @ResponseBody
    public LinkedHashMap<String, Object> registerWithAvatar(MultipartFile img, String session_key, String avatar_url,
                                     String nickName, String phoneNumber, int gender) throws IOException {
        /* 判断是否已有同名或同手机号的用户 */
        LinkedHashMap<String, Object> res = loginService.checkRegInfo(session_key, nickName, phoneNumber);
        // 含有错误信息，返回
        if(res != null) {
            return res;
        }
        /* 添加用户记录，保存图片 */
        res = loginService.addUser(session_key, avatar_url, nickName, phoneNumber, gender, img);

        return res;
    }

    /**
     * 接收无上传头像的注册数据，存储数据库并绑定，返回处理结果和uid
     * @param session_key 当前会话密钥
     * @param avatar_url 头像url
     * @param nickName 用户昵称
     * @param phoneNumber 用户电话号码
     * @param gender 用户性别
     * @return {info: 处理结果, uid: 用户uid}
     */
    @PostMapping("register/url")
    @ResponseBody
    public LinkedHashMap<String, Object> registerWithUrl(String session_key, String avatar_url, String nickName,
                             String phoneNumber, int gender) throws IOException {
        System.out.println(nickName);
        /* 判断是否已有同名或同手机号的用户 */
        LinkedHashMap<String, Object> res = loginService.checkRegInfo(session_key, nickName, phoneNumber);
        // 含有错误信息，返回
        if(res != null) {
            return res;
        }
        /* 添加用户记录 */
        res = loginService.addUser(session_key, avatar_url, nickName, phoneNumber, gender, null);

        return res;
    }
}
