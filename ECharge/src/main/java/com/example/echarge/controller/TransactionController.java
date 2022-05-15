package com.example.echarge.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trans")
@CrossOrigin(origins = "*")
public class TransactionController {
    //获取订单列表 list
    //任务分派 assign
    //购买商品 buy
    //确认购买 confirm_buy
    //确认收货/任务结束 confirm_finish
    //
}
