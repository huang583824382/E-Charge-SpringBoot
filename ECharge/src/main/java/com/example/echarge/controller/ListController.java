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

import java.util.LinkedHashMap;
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
     * @return 获取的商品列表
     */
    @PostMapping("goods")
    @ResponseBody
    public List<CommodityEntity> getGoodsList(String token, int size) {
        UserEntity user = userService.getUserByToken(token);
        // token不对，无效查询
        if(user == null) {
           return null;
        }
        return listService.getList(0, size);
    }

    /**
     * 获取任务列表
     * @param token 用户token
     * @param size 数量
     * @return 获取的任务列表
     */
    @PostMapping("tasks")
    @ResponseBody
    public List<CommodityEntity> getTasksList(String token, int size){
        UserEntity user = userService.getUserByToken(token);
        // token不对，无效查询
        if(user == null) {
            return null;
        }
        System.out.println("123");
        System.out.println("456");
        return listService.getList(1, size);
    }

    /**
     * 根据搜索关键词获取列表
     * @param token 用户token
     * @param size 数量
     * @param search 检索信息
     * @param order 排序顺序 1: 价格升序；2: 价格降序；3: 时间倒序
     * @return {list: 获取的列表; totalCount: 符合条件项总数; code: 请求状态}
     */
    @PostMapping("search")
    @ResponseBody
    public LinkedHashMap<String, Object> getSearchList(String token, int type, int size, String search, int order, int lastId){
        UserEntity user = userService.getUserByToken(token);
        LinkedHashMap<String, Object> res = new LinkedHashMap<String, Object>(0);
        System.out.println("999");
        // token不对，无效查询
        if(user == null) {
            res.put("code", "fail");
            return res;
        }
        System.out.println("123");
        // 有条件查询
        res = listService.getSelectedList(type, size, search, order, lastId);
        res.put("code", "success");

        return res;
    }
}
