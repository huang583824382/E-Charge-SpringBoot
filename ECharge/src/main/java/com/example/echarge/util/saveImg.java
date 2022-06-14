package com.example.echarge.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class saveImg {

    @Value("${upload-url}") private static String uploadUrl;
    @Value("${upload-path}") private static String uploadPath;
    /**
     * 保存一张图片
     *
     * @param img  图片流
     * @param path 图片路径
     */
    public static void saveOneImg(MultipartFile img, String path) {
        try {
            // 保存图片到本地
            img.transferTo(new java.io.File(path));
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    /**
     * 保存一组图片
     * @param imgs 图片流列表
     * @param paths 图片路径列表
     * @return true - 成功保存 false - 保存出错
     */
    public static Boolean saveImgs(MultipartFile[] imgs, String[] paths) {
        try {
            // 遍历列表
            for(int i = 0; i < imgs.length; i++) {
                saveOneImg(imgs[i], paths[i]);
            }
            return true;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static String saveImage(MultipartFile img, String path) {
        String fid = UUID.randomUUID().toString().replace("-", "");
        String tmpPath = path + fid + ".jpg";
        System.out.println(tmpPath);
        // 创建目录
        File Folder = new File(path);
        if(!Folder.exists() || !Folder.isDirectory()) Folder.mkdirs();
        // 保存头像
        try {
            // 保存图片到本地
            img.transferTo(new java.io.File(tmpPath));
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return fid + ".jpg";
    }
}
