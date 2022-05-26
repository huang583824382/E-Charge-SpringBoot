package com.example.echarge.controller;

import com.example.echarge.dao.TransactionDao;
import com.example.echarge.entity.CommentEntity;
import com.example.echarge.entity.CommodityEntity;
import com.example.echarge.entity.TransactionEntity;
import com.example.echarge.entity.UserEntity;
import com.example.echarge.service.CommodityService;
import com.example.echarge.service.TransactionService;
import com.example.echarge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;

@RestController
@RequestMapping("/trans")
@CrossOrigin(origins = "*")
public class TransactionController {
    @Autowired
    UserService userService;
    @Autowired
    CommodityService commodityService;
    @Autowired
    TransactionService transactionService;
    //获取订单列表 list
    @PostMapping("list")
    @ResponseBody
    public LinkedList<Object> getTransactionList(String token, int lastIndex, int state, int isCustomer){
        System.out.println("customer"+isCustomer);
        UserEntity user = userService.getUserByToken(token);
        LinkedList<Object> res = transactionService.getTransactionListByStateAndIndex(user.getUid(),state, lastIndex, isCustomer);
        return res;
    }
    @PostMapping("detail")
    @ResponseBody
    public LinkedHashMap<String, Object> getTransactionDetail(String token, int transactionID){
        UserEntity user = userService.getUserByToken(token);
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        if(user!=null){
            res = transactionService.getTransactionDetail(transactionID); //可能可以判断uid是否是订单中有权限的用户
        }
        else{
            res.put("Code", "fail");
        }
        return res;
    }

    @PostMapping("rate")
    @ResponseBody
    public LinkedHashMap<String, Object> rateForTrans(String token, int transId, double rate){
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        UserEntity user = userService.getUserByToken(token);
        CommentEntity comment = new CommentEntity();
        comment.setTransactionId(transId);
        comment.setRate(rate);
        comment.setUid(user.getUid());
        transactionService.addComment(comment);
        TransactionEntity trans = transactionService.getTransByState(transId, 3);
        trans.setState(4);
        transactionService.updateTrans(trans);
        res.put("code", "success");
        return res;
    }


    @PostMapping("info")
    @ResponseBody
    public LinkedList<Object> getTransactionInfo(String token){
        UserEntity user = userService.getUserByToken(token);
        if(user!=null){
            return transactionService.getTransactionInfo(user.getUid());
        }
        else{
            return null;
        }
    }
    //任务分派 assign


    //生成订单 buy
    @PostMapping("/buy")
    @ResponseBody
    public LinkedHashMap<String, Object> genTransaction(String token, int itemId) {
        LinkedHashMap<String, Object> res = new LinkedHashMap<String, Object>(0);
        // 检查token
        UserEntity user = userService.getUserByToken(token);
        if(user == null) {
            res.put("code", "fail");
            return res;
        }
        // 检查itemId
        if(!commodityService.canBeBought(itemId)) {
            res.put("code", "has been bought");
            return res;
        }
        // 设置commodity状态为1，即已被购买
        CommodityEntity comm = commodityService.getByItemId(itemId);
        comm.setState(1);
        commodityService.updateCommodity(comm);
        // 可以创建，插入数据
        TransactionEntity trans = new TransactionEntity();
        trans.setItemId(itemId);
        trans.setCustomerId(user.getUid());
        trans.setState(1);
        trans.setSellerId(user.getUid());
        trans.setType(comm.getType());
        trans.setDealTime(new Timestamp((new Date()).getTime()));
        trans = transactionService.addTrans(trans);
        // 设置commodity状态为1，即已被购买
        comm.setState(1);
        trans.setSellerId(comm.getPubId());
        trans.setType(comm.getType());
        commodityService.updateCommodity(comm);
        res.put("code", "success");
        res.put("transId", trans.getTransactionId());
        return res;
    }
    //付款 pay
    @PostMapping("/pay")
    @ResponseBody
    public LinkedHashMap<String, Object> payTransaction(String token, int transId) {
        LinkedHashMap<String, Object> res = new LinkedHashMap<String, Object>(0);
        // 检查token和transactionId
        UserEntity user = userService.getUserByToken(token);
        TransactionEntity trans = transactionService.getPayableTrans(transId);
        if(user == null || trans == null) {
            res.put("code", "fail");
            return res;
        }
        // 有订单数据，获取对应的商品
        CommodityEntity comm = commodityService.getByItemId(trans.getItemId());
        // 判断余额是否足够
        if(user.getBalance() < comm.getPrice()) {
            res.put("code", "insufficient balance");
            return res;
        }
        // 更新transaction
        trans.setState(2);
        transactionService.updateTrans(trans);
        // 更新user余额
        user.setBalance(user.getBalance()-comm.getPrice());
        userService.updateUser(user);
        res.put("code", "success");
        return res;
    }

    //确认收货/任务结束 confirm
    @PostMapping("/confirm")
    @ResponseBody
    public LinkedHashMap<String, Object> confirmReceipt(String token, int transId) {
        LinkedHashMap<String, Object> res = new LinkedHashMap<String, Object>(0);
        // 检查token和transactionId
        UserEntity user = userService.getUserByToken(token);
        TransactionEntity trans = transactionService.getTransByState(transId, 2);
        if(user == null || trans == null) {
            res.put("code", "fail");
            return res;
        }
        // 有订单数据，获取对应的商品
        CommodityEntity comm = commodityService.getByItemId(trans.getItemId());
        // 更新transaction状态
        trans.setState(3);
        transactionService.updateTrans(trans);
        res.put("code", "success");
        return res;
    }

    // 取消订单
    @PostMapping("/cancel")
    @ResponseBody
    public LinkedHashMap<String, Object> cancelTransaction(String token, int transId) {
        LinkedHashMap<String, Object> res = new LinkedHashMap<String, Object>(0);
        // 检查token和transactionId
        UserEntity user = userService.getUserByToken(token);
        TransactionEntity trans = transactionService.getPayableTrans(transId);
        if(user == null || trans == null) {
            res.put("code", "fail");
            return res;
        }
        // 有订单数据，获取对应的商品
        CommodityEntity comm = commodityService.getByItemId(trans.getItemId());
        // 更新transaction
        trans.setState(0);
        transactionService.updateTrans(trans);
        // 更新commodity
        comm.setState(0);
        commodityService.updateCommodity(comm);

        res.put("code", "success");
        return res;
    }

    // 退款
    @PostMapping("/refund")
    @ResponseBody
    public LinkedHashMap<String, Object> dealWithRefund(String token, int transId) {
        LinkedHashMap<String, Object> res = new LinkedHashMap<String, Object>(0);
        // 检查token和transactionId
        UserEntity user = userService.getUserByToken(token);
        TransactionEntity trans = transactionService.getTransByState(transId, 2);
        if(user == null || trans == null) {
            res.put("code", "fail");
            return res;
        }
        // 有订单数据，获取对应的商品
        CommodityEntity comm = commodityService.getByItemId(trans.getItemId());
        // 更新transaction状态
        trans.setState(5);
        transactionService.updateTrans(trans);
        // 更新user余额
        user.setBalance(user.getBalance()+comm.getPrice());
        userService.updateUser(user);

        res.put("code", "success");
        return res;
    }
}
