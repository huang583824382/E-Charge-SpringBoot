package com.example.echarge.controller;

import com.example.echarge.entity.UserEntity;
import com.example.echarge.service.ReportService;
import com.example.echarge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/report")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    UserService userService;

    @Autowired
    ReportService reportService;

    // 有图片举报，首张图片
    @PostMapping("first") //
    @ResponseBody
    public LinkedHashMap<String, Object> reportFirst(MultipartFile image, String token, int target_id,
                                                      String imageUrl, String reason, int isUser) throws IOException {
        System.out.println(isUser);
        /* 这里用于传递第一张图片时的情况 传入整个信息，之后不用传session_key 和 url 以外的信息 */
        /* 添加商品记录，保存图片 */
        UserEntity user = userService.getUserByToken(token);
        LinkedHashMap<String, Object> res;
        if(user == null) {
            res = new LinkedHashMap<>(0);
            res.put("code", "fail");
        }
        else {
            res = reportService.addReportFirst(image, imageUrl, reason, target_id, isUser, user.getUid());
        }
        return res;

    }

    // 有图片举报，后续图片
    @PostMapping("second")
    @ResponseBody
    public LinkedHashMap<String, Object> reportSecond(MultipartFile image, String imageUrl, String token, int report_id) throws IOException {
        /* 这里用于传递第一张图片时的情况 传入整个信息，之后不用传session_key 和 url 以外的信息 */
        UserEntity user = userService.getUserByToken(token);
        LinkedHashMap<String, Object> res;
        if(user == null) {
            res = new LinkedHashMap<>(0);
            res.put("code", "fail");
        }
        else {
            res = reportService.addReportSecond(image, imageUrl, report_id);
        }
        return res;
    }

    // 无图片举报
    @PostMapping("no-pic")
    @ResponseBody
    public LinkedHashMap<String, Object> reportWithNoPic(String token, String reason, int target_id, int isUser) {
        UserEntity user = userService.getUserByToken(token);
        if(user == null) {
            LinkedHashMap<String, Object> res = new LinkedHashMap<>(0);
            res.put("code", "fail");
            return res;
        }
        return reportService.addReportWithNoPic(reason, target_id, isUser, user.getUid());
    }


}
