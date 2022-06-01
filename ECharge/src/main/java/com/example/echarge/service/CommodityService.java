package com.example.echarge.service;

import com.example.echarge.dao.CommodityDao;
import com.example.echarge.entity.CommodityEntity;
import com.example.echarge.util.saveImg;
import org.hibernate.engine.transaction.jta.platform.internal.OC4JJtaPlatform;
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
public class CommodityService {
    @Autowired
    CommodityDao commodityDao;

    public CommodityEntity getByItemId(int itemId) {
        return commodityDao.findByItemId(itemId);
    }

    public boolean canBeBought(int itemId) {
        return commodityDao.findByItemIdAndState(itemId, 0) != null;
    }

    public void updateCommodity(CommodityEntity comm) {
        commodityDao.saveAndFlush(comm);
    }

    @Value("${upload-url}") private String uploadUrl;
    @Value("${upload-path}") private String uploadPath;
    private LinkedHashMap<String, Object> assembleRetVal(String info, Integer item_id, String title, String publishImage) {
        LinkedHashMap<String, Object> res = new LinkedHashMap<String, Object>(0);
        res.put("info", info);
        res.put("itemId", item_id);
        res.put("name", title);
        res.put("avatar",publishImage);
        return res;
    }
    public LinkedHashMap<String, Object> addCommodityFirst(String session_key,MultipartFile publish, String title,
                                                           String publishImage, double price, String description,
                                                           String tags, String place, String deadline,
                                                           int type, int uid, int state) throws IOException {
        // 保存头像
        String f_url;
        if(publish != null) {
            String fid = UUID.randomUUID().toString().replace("-", "");
            f_url = uploadUrl + fid + ".jpg";
            String f_path = uploadPath + fid + ".jpg";
            System.out.println(f_path);
            // 创建目录
            File Folder = new File(uploadPath);
            if(!Folder.exists() || !Folder.isDirectory()) Folder.mkdirs();
            // 保存头像
            saveImg.saveOneImg(publish, f_path);
        }
        else {
            f_url = publishImage;
        }
        // 更新发布
        System.out.println(type);
        System.out.println(price);
        CommodityEntity publishItem = new CommodityEntity(); // 保证有对应用户记录
        publishItem.setDescription(description);
        publishItem.setPubId(uid);
        publishItem.setPrice(price);
        publishItem.setTitle(title);
        publishItem.setType(type);
        publishItem.setState(state);
        publishItem.setReleaseTime(new Timestamp(new Date().getTime()));
        publishItem.setFigureUrls(f_url);
        if (tags == null) {
            tags = "";
        }
        publishItem.setTags(tags);
        if(type == 1) {
            publishItem.setPlace(place);
            publishItem.setTimeExpected(deadline);
        }
        commodityDao.saveAndFlush(publishItem);
        return assembleRetVal("success", publishItem.getItemId(),publishItem.getTitle(),publishItem.getFigureUrls());

    }
    public LinkedHashMap<String, Object> addCommoditySecond(String session_key,MultipartFile publish, int item_id, String publishImage) throws IOException {
        // 保存头像
        String f_url;
        if(publish != null) {
            String fid = UUID.randomUUID().toString().replace("-", "");
            f_url = uploadUrl + fid + ".jpg";
            String f_path = uploadPath + fid + ".jpg";
            // 保存头像
            saveImg.saveOneImg(publish, f_path);
        }
        else {
            f_url = publishImage;
        }
        CommodityEntity comm = commodityDao.findByItemId(item_id);
        comm.setFigureUrls(comm.getFigureUrls()+";"+f_url);
        commodityDao.saveAndFlush(comm);
        return assembleRetVal("success", null, null, null);
    }

    public LinkedHashMap<String, Object> addNoPicTask(String title, double price, String description,
                                                      String tags, String place, String deadline, int uid, int state) {
        CommodityEntity publishItem = new CommodityEntity(); // 保证有对应用户记录
        publishItem.setDescription(description);
        publishItem.setPubId(uid);
        publishItem.setPrice(price);
        publishItem.setTitle(title);
        publishItem.setType(1);
        publishItem.setState(state);
        publishItem.setReleaseTime(new Timestamp(new Date().getTime()));
        publishItem.setFigureUrls("");
        if (tags == null) {
            tags = "";
        }
        publishItem.setTags(tags);
        publishItem.setPlace(place);
        publishItem.setTimeExpected(deadline);
        commodityDao.saveAndFlush(publishItem);
        LinkedHashMap<String, Object> res = new LinkedHashMap<>(0);
        res.put("code", "success");
        return res;
    }


    public CommodityEntity getCommodityByItemId(int item_id) {
        return commodityDao.findByItemId(item_id);
    }

    public void takeDownCommodityByItemId(int itemId) {
        CommodityEntity comm = commodityDao.findByItemId(itemId);
        comm.setState(3);
        commodityDao.saveAndFlush(comm);
    }
}
