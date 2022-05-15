package com.example.echarge.controller;

import com.example.echarge.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/start")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("login") //接收客户端传来的wx通行证，与wx服务器通信，返回登录结果
    @ResponseBody
    public Object loginin(HttpServletRequest Request, HttpServletResponse response,  String code){
        System.out.println("recieved "+code);
//        请求微信服务器
        LinkedHashMap<String, Object> data = loginService.getUserInfo(code);
        return data;
    }
    @PostMapping("register") //接收注册数据，存储数据库并绑定，与wx服务器通信，返回登录结果
    @ResponseBody
    public Object register(HttpServletRequest Request, HttpServletResponse response){
        return null;
    }
    //

}
