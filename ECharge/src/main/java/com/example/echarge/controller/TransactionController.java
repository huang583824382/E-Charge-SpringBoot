package com.example.echarge.controller;

import com.example.echarge.entity.UserEntity;
import com.example.echarge.service.TransactionService;
import com.example.echarge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
@RequestMapping("/trans")
@CrossOrigin(origins = "*")
public class TransactionController {
    @Autowired
    UserService userService;
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
    //购买商品 buy
    //确认购买 confirm_buy
    //确认收货/任务结束 confirm_finish
    //
}
