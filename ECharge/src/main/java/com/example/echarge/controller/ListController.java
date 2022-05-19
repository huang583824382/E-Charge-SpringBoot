/**
 * 文 件 名 : ListController.java
 * 描 述 : 主页和搜索获取列表的控制器
 * 最后修改时间 : 2022-5-18 12:33 am
 */

package com.example.echarge.controller;

import com.example.echarge.entity.CommodityEntity;
import com.example.echarge.entity.UserEntity;
import com.example.echarge.service.ListService;
import com.example.echarge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
@CrossOrigin(origins = "*")
public class ListController {

    @Autowired
    ListService listService;

    @Autowired
    UserService userService;

    /**
     * 获取商品列表
     * @param token 用户token
     * @param size 数量
     * @param search 检索信息
     * @return 获取的商品列表
     */
    @PostMapping("goods")
    @ResponseBody
    public List<CommodityEntity> getGoodsList(String token, int size, String search){
        UserEntity user = userService.getUserByToken(token);
        // token不对，无效查询
        if(user == null) {
           return null;
        }
        // 无条件查询
        if(search.equals("")) {
            return listService.getList(0, size);
        }
        // 有条件查询
        else {
            return  listService.getSelectedList(0, size, search);
        }
    }

    /**
     * 获取任务列表
     * @param token 用户token
     * @param size 数量
     * @param search 检索信息
     * @return 获取的任务列表
     */
    @PostMapping("tasks")
    @ResponseBody
    public List<CommodityEntity> getTasksList(String token, int size, String search){
        UserEntity user = userService.getUserByToken(token);
        // token不对，无效查询
        if(user == null) {
            return null;
        }
        System.out.println("123");
        // 无条件查询
        if(search.equals("")) {
            System.out.println("456");
            return listService.getList(1, size);
        }
        // 有条件查询
        else {
            return  listService.getSelectedList(1, size, search);
        }
    }
}
