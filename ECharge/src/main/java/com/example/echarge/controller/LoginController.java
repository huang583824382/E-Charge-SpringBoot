package com.example.echarge.controller;

import com.example.echarge.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/start")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    LoginService start;

    @GetMapping("login") //接收客户端传来的wx通行证，与wx服务器通信，返回登录结果
    @ResponseBody
    public Object loginin(HttpServletRequest Request, HttpServletResponse response){
        return "null";
    }
    @PostMapping("register") //接收注册数据，存储数据库并绑定，与wx服务器通信，返回登录结果
    @ResponseBody
    public Object register(HttpServletRequest Request, HttpServletResponse response){
        return null;
    }
    //获取用户数据详情
    //

}
