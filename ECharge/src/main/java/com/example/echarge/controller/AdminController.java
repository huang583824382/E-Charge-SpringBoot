package com.example.echarge.controller;

import com.example.echarge.entity.UserEntity;
import com.example.echarge.service.AdminService;
import com.example.echarge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @PostMapping("list")
    @ResponseBody
    public LinkedHashMap<String, Object> getAdminList(String token){
        UserEntity user = userService.getUserByToken(token);
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        if (user == null){
            res.put("code", "fail");
            return res;
        }
        if(user.getIsAdmin()!=1){
            res.put("code", "fail");
            return res;
        }
        res = adminService.getList();
        res.put("code", "success");
        return res;
    }

    @PostMapping("report")
    @ResponseBody
    public LinkedHashMap<String, Object> getReportList(String token){
        UserEntity user = userService.getUserByToken(token);
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        if (user == null){
            res.put("code", "fail");
            return res;
        }
        if(user.getIsAdmin()!=1){
            res.put("code", "fail");
            return res;
        }
        res = adminService.getReport();
        res.put("code", "success");
        return res;
    }

    @PostMapping("review")
    @ResponseBody
    public LinkedHashMap<String, Object> getReviewList(String token){
        UserEntity user = userService.getUserByToken(token);
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        if (user == null){
            res.put("code", "fail");
            return res;
        }
        if(user.getIsAdmin()!=1){
            res.put("code", "fail");
            return res;
        }
//        res = adminService.getList();
        res = adminService.getReview();
        res.put("code", "success");
        return res;
    }

    @PostMapping("report/detail")
    @ResponseBody
    public LinkedHashMap<String, Object> getReportDetail(String token, int report_id){
        UserEntity user = userService.getUserByToken(token);
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        if (user == null){
            res.put("code", "fail");
            return res;
        }
        if(user.getIsAdmin()!=1){
            res.put("code", "fail");
            return res;
        }
//        res = adminService.getList();
        res = adminService.getReportDetail(report_id);
        res.put("code", "success");
        return res;
    }

    @PostMapping("punish")
    @ResponseBody
    public LinkedHashMap<String, Object> handleReport(String token, int report_id, int option_id, int offShelf){
        UserEntity user = userService.getUserByToken(token);
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        if (user == null){
            res.put("code", "fail");
            return res;
        }
        if(user.getIsAdmin()!=1){
            res.put("code", "fail");
            return res;
        }
//        res = adminService.getList();
        System.out.println(""+report_id+" "+option_id+" "+offShelf);
        if(adminService.handleReport(report_id, option_id, offShelf)){
            res.put("code", "success");
        }
        else {
            res.put("code", "fail");
        }
        return res;
    }

    @PostMapping("review/handle")
    @ResponseBody
    public LinkedHashMap<String, Object> handleReview(String token, int commodity_id, int option_id){
        UserEntity user = userService.getUserByToken(token);
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        if (user == null){
            res.put("code", "fail");
            return res;
        }
        if(user.getIsAdmin()!=1){
            res.put("code", "fail");
            return res;
        }
//        res = adminService.getList();
        if(adminService.reviewCommodity(commodity_id, option_id)){
            res.put("code", "success");
        }
        else{
            res.put("code", "fail");
        }
        return res;
    }
    //获取待处理信息 list
    //获取待审核列表
    //审核商品
    //获取举报列表
    //处理举报（会向对象发送处理结果）
    //
}
