package com.example.echarge.controller;

import com.example.echarge.entity.CommodityEntity;
import com.example.echarge.entity.UserEntity;
import com.example.echarge.service.CommodityService;
import com.example.echarge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    // 发布有图片商品
    @PostMapping("publish/first") //
    @ResponseBody
    public LinkedHashMap<String, Object> publishFirst(MultipartFile publish, String session_key, String title,
                                                      String publishImage, double price, String description, String tags,
                                                      String place, String deadline, int type) throws IOException {
        System.out.println(session_key);
        /* 这里用于传递第一张图片时的情况 传入整个信息，之后不用传session_key 和 url 以外的信息 */
        /* 添加商品记录，保存图片 */
        UserEntity user = userService.getUserByToken(session_key);
        if(user == null) {
            LinkedHashMap<String, Object> res = new LinkedHashMap<>(0);
            res.put("info", "verification error");
            return res;
        }
        else {
            int state = (user.getCredit() < 80? -1: 0); // 0: to be bought; -1: to be audited
            LinkedHashMap<String, Object> res = commodityService.addCommodityFirst(session_key, publish, title, publishImage, price, description,
                                                                                   tags, place, deadline, type, user.getUid(), state);
            return res;
        }

    }
    @PostMapping("publish/second")
    @ResponseBody
    public LinkedHashMap<String, Object> publishSecond(MultipartFile publish, String publishImage, String session_key, int item_id) throws IOException {
        /* 这里用于传递第一张图片时的情况 传入整个信息，之后不用传session_key 和 url 以外的信息 */
        UserEntity user = userService.getUserByToken(session_key);
        LinkedHashMap<String, Object> res;
        if(user == null) {
            res = new LinkedHashMap<>(0);
            res.put("info", "verification error");
        }
        else {
            res = commodityService.addCommoditySecond(session_key, publish, item_id, publishImage);
        }
        return res;
    }

    // 无图片任务发布
    @PostMapping("publish/no-pic") //
    @ResponseBody
    public LinkedHashMap<String, Object> publishNoPicTask(String session_key, String title,
                                                          double price, String description, String tags,
                                                          String place, String deadline) {
        UserEntity user = userService.getUserByToken(session_key);
        if(user == null) {
            LinkedHashMap<String, Object> res = new LinkedHashMap<>(0);
            res.put("code", "verification error");
            return res;
        }
        int state = (user.getCredit() < 80? -1: 0); // 0: to be bought; -1: to be audited
        return commodityService.addNoPicTask(title, price, description, tags, place, deadline, user.getUid(), state);
    }

    //编辑商品

    //删除商品
    @PostMapping("delete") //
    @ResponseBody
    public LinkedHashMap<String, Object> deleteCommodity(String token, int itemId) {
        UserEntity user = userService.getUserByToken(token);
        CommodityEntity comm = commodityService.getByItemId(itemId);
        LinkedHashMap<String, Object> res = new LinkedHashMap<>(0);
        if(user == null || comm == null) {
            res.put("code", "fail");
            return res;
        }
        if(comm.getPubId() != user.getUid()) {
            res.put("code", "no right");
            return res;
        }
        commodityService.takeDownCommodityByItemId(comm.getItemId());
        res.put("code", "success");
        return res;
    }
}
