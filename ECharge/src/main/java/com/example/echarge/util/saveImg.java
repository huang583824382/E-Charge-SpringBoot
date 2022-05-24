package com.example.echarge.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class saveImg {
    /**
     * 保存一张图片
     * @param img 图片流
     * @param path 图片路径
     * @return true - 成功保存 false - 保存出错
     */
    public static Boolean saveOneImg(MultipartFile img, String path) throws IOException {
        try {
            // 保存图片到本地
            img.transferTo(new java.io.File(path));
            return true;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }
    /**
     * 保存一组图片
     * @param imgs 图片流列表
     * @param paths 图片路径列表
     * @return true - 成功保存 false - 保存出错
     */
    public static Boolean saveImgs(MultipartFile[] imgs, String[] paths) throws  IOException {
        try {
            // 遍历列表
            for(int i = 0; i < imgs.length; i++) {
                saveOneImg(imgs[i], paths[i]);
            }
            return true;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }
}
