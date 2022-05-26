package com.example.echarge.controller;

import com.example.echarge.entity.CommodityEntity;
import com.example.echarge.entity.UserEntity;
import com.example.echarge.service.CommodityService;
import com.example.echarge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/commodity")
@CrossOrigin(origins = "*")
public class CommodityController {

    @Autowired
    UserService userService;

    @Autowired
    CommodityService commodityService;

    //获取商品详情
    @PostMapping("info")
    @ResponseBody
    public LinkedHashMap<String, Object> getCommodityInfo(String token, int itemId) {
        LinkedHashMap<String, Object> res = new LinkedHashMap<>(0);
        UserEntity user = userService.getUserByToken(token);
        CommodityEntity comm = commodityService.getByItemId(itemId);
        if(user == null || comm == null) {
            res.put("code", "fail");
            return res;
        }
        res.put("commodity", comm);
        res.put("code", "success");
        return res;
    }

    //发布商品
    //编辑商品
    //删除商品
    //
}
