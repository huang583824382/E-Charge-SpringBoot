package com.example.echarge.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
@CrossOrigin("/wx") //应当只允许微信服务器访问，暂未知道服务器
public class WxServerController {
    //接收微信服务器数据
}
