package com.example.echarge.service;

import com.example.echarge.dao.CommodityDao;
import com.example.echarge.dao.ReportDao;
import com.example.echarge.dao.UserDao;
import com.example.echarge.entity.CommodityEntity;
import com.example.echarge.entity.ReportEntity;
import com.example.echarge.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.echarge.util.getCoverUtil.getCover;
import static com.example.echarge.util.getCoverUtil.getUrlList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    CommodityDao commodityDao;
    @Autowired
    ReportDao reportDao;
    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;
    public LinkedHashMap<String, Object> getList(){
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        List<CommodityEntity> toReview = commodityDao.findByState(-1);
        res.put("toReview", toReview.size());
        List<ReportEntity> toReport = reportDao.findByState(0);
        res.put("toReport", toReport.size());
        return res;
    }
    public LinkedHashMap<String, Object> getReview(){
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        List<CommodityEntity> toReview = commodityDao.findByState(-1);
        List<Object> tmpReview = new ArrayList<>();
        for(CommodityEntity item : toReview){
            LinkedHashMap<String, Object> tmp = new LinkedHashMap<>();
            tmp.put("commodity_id", item.getItemId());
            tmp.put("title", item.getTitle());
            tmp.put("price", item.getPrice());
            tmp.put("deal_time", item.getReleaseTime());
            tmp.put("figure_url", getCover(item.getFigureUrls()));
            tmpReview.add(tmp);
        }
        res.put("Review", tmpReview);
        return res;
    }
    public LinkedHashMap<String, Object> getReport(){
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        List<ReportEntity> toReport = reportDao.findByState(0);
        res.put("Report", toReport);
        return res;
    }

    public LinkedHashMap<String, Object> getReportDetail(int report_id){
        ReportEntity report = reportDao.findByReportId(report_id);
        String[] urls = getUrlList(report.getUrls());
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        res.put("report_id", report.getReportId());
        res.put("report_title", report.getReportTitle());
        res.put("type", report.getType());
        res.put("target_user", report.getTargetUserId());
        res.put("target_item", report.getTargetItemId());
        res.put("whistleblower", report.getWhistleblowerId());
        res.put("report_time", report.getReportTime());
        res.put("state", report.getState());
        res.put("urls", urls);
        UserEntity targetUser = userService.showUserInfo(report.getTargetUserId());
        res.put("target_user_name", targetUser.getName());
        res.put("target_user_avatar", targetUser.getIconUrl());
        if(report.getTargetItemId() != null){
            CommodityEntity commodity = commodityDao.findByItemId(report.getTargetItemId());
            res.put("target_item_title", commodity.getTitle());
            res.put("target_item_cover", getCover(commodity.getFigureUrls()));
        }
        return res;
    }
    public boolean handleReport(int report_id, int option_id, int offShelf){
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        ReportEntity report = reportDao.findByReportId(report_id);
        UserEntity user = userDao.findByUid(report.getTargetUserId());
        switch (option_id){
            case 0:
                //驳回
                System.out.println("no punish");
                report.setOptionId(option_id);
                break;
            case 1:
                //警告
                System.out.println("punish 0 credit");
                report.setOptionId(option_id);
                break;
            case 2:
                //扣1分
                System.out.println("punish 1 credit");
                report.setOptionId(option_id);
                user.setCredit(user.getCredit()-1);
                break;
            case 3:
                //扣3分
                System.out.println("punish 3 credit");
                report.setOptionId(option_id);
                user.setCredit(user.getCredit()-3);
                break;
        }
        if(offShelf == 1 && report.getTargetItemId()!=null){
            //下架商品
            CommodityEntity commodity = commodityDao.findByItemId(report.getTargetItemId());
            commodity.setState(2); //-1待审0待收1售出2下架
            commodityDao.saveAndFlush(commodity);
        }
        report.setState(1);
        userDao.saveAndFlush(user);
        reportDao.saveAndFlush(report);
        return true;
    }
    public boolean reviewCommodity(int commodity_id, int option_id){
        CommodityEntity commodity = commodityDao.findByItemId(commodity_id);
        if(option_id!=0&&option_id!=2){
            return false;
        }
        commodity.setState(option_id); //0上架，2下架
        commodityDao.saveAndFlush(commodity);
        return true;
    }
}
