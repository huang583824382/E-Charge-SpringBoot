package com.example.echarge.service;

import com.example.echarge.dao.CommodityDao;
import com.example.echarge.dao.ReportDao;
import com.example.echarge.entity.ReportEntity;
import com.example.echarge.util.saveImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.UUID;

@Service
public class ReportService {

    @Autowired
    ReportDao reportDao;
    @Autowired
    CommodityDao commodityDao;

    @Value("${upload-url}") private String uploadUrl;
    @Value("${upload-path}") private String uploadPath;

    public LinkedHashMap<String, Object> addReportFirst(MultipartFile image, String imageUrl, String reason, int target_id, int isUser, int uid) throws IOException {
        // 保存图片
        String f_url;
        if (image != null) {
            String fid = UUID.randomUUID().toString().replace("-", "");
            f_url = uploadUrl + fid + ".jpg";
            String f_path = uploadPath + fid + ".jpg";
            System.out.println(f_path);
            // 创建目录
            File Folder = new File(uploadPath);
            if (!Folder.exists() || !Folder.isDirectory()) Folder.mkdirs();
            // 保存图片
            saveImg.saveOneImg(image, f_path);
        } else {
            f_url = imageUrl;
        }
        ReportEntity report = new ReportEntity();
        report.setReportTime(new Timestamp(new Date().getTime()));
        report.setReportTitle(reason);
        report.setType(isUser == 1 ? 0 : 1);
        report.setState(0);
        report.setUrls(f_url);
        report.setWhistleblowerId(uid);
        if (isUser == 1) {
            report.setTargetUserId(target_id);
        } else {
            report.setTargetItemId(target_id);
            report.setTargetUserId(commodityDao.findByItemId(target_id).getPubId());
        }
        reportDao.saveAndFlush(report);
        LinkedHashMap<String, Object> res = new LinkedHashMap<>(0);
        res.put("code", "success");
        res.put("reportId", report.getReportId());
        return res;
    }

    public LinkedHashMap<String, Object> addReportSecond(MultipartFile image, String imageUrl, int reportId) throws IOException {
        // 保存图片
        String f_url;
        if (image != null) {
            String fid = UUID.randomUUID().toString().replace("-", "");
            f_url = uploadUrl + fid + ".jpg";
            String f_path = uploadPath + fid + ".jpg";
            System.out.println(f_path);
            // 创建目录
            File Folder = new File(uploadPath);
            if (!Folder.exists() || !Folder.isDirectory()) Folder.mkdirs();
            // 保存图片
            saveImg.saveOneImg(image, f_path);
        } else {
            f_url = imageUrl;
        }

        ReportEntity report = reportDao.findByReportId(reportId);
        report.setUrls(report.getUrls() + ";" + f_url);
        reportDao.saveAndFlush(report);

        LinkedHashMap<String, Object> res = new LinkedHashMap<>(0);
        res.put("code", "success");
        return res;
    }

    public LinkedHashMap<String, Object> addReportWithNoPic(String reason, int target_id, int isUser, int uid) {
        ReportEntity report = new ReportEntity();
        report.setReportTime(new Timestamp(new Date().getTime()));
        report.setReportTitle(reason);
        report.setType(isUser == 1 ? 0 : 1);
        report.setState(0);
        report.setUrls("");
        report.setWhistleblowerId(uid);
        if (isUser == 1) {
            report.setTargetUserId(target_id);
        } else {
            report.setTargetItemId(target_id);
        }
        reportDao.saveAndFlush(report);
        LinkedHashMap<String, Object> res = new LinkedHashMap<>(0);
        res.put("code", "success");
        return res;
    }

}
