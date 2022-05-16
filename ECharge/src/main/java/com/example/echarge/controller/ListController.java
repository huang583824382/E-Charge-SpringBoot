package com.example.echarge.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@RestController
@RequestMapping("/list")
@CrossOrigin(origins = "*")
public class ListController {
    //获取商品列表
    @PostMapping("goods")
    @ResponseBody
    public Object getGoodsList(HttpServletRequest Request, HttpServletResponse response, String search){
        Cookie[] cookies = Request.getCookies();
        System.out.println("cookie "+" "+cookies[0].getValue());
        return "test get goods list";
    }

    @PostMapping("tasks")
    @ResponseBody
    public Object getTasksList(HttpServletRequest Request, HttpServletResponse response, String search){
        return "test get tasks list";
    }
    //获取任务列表
    //搜索结果
    //
}
