/**
 * 文 件 名 : ListService.java
 * 描 述 : 主页和搜索展示列表对应的服务
 * 最后修改时间 : 2022-5-18 12:18 am
 */

package com.example.echarge.service;

import com.example.echarge.dao.CommodityDao;
import com.example.echarge.entity.CommodityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.min;

@Service
public class ListService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    CommodityDao commodityDao;

    /**
     * 无筛选条件随机获取commodities
     * @param type 类型 0：商品；1：任务
     * @param size 数量
     * @return 获取的commodities列表
     */
    public List<CommodityEntity> getList(int type, int size) {
        List<CommodityEntity> list = commodityDao.findAllByType(type);
        // 随机排序
        Collections.shuffle(list);
        return list.subList(0, min(size, list.size()));
    }
    /**
     * 有筛选条件随机获取commodities，在标题和标签中查找对应字段
     * @param type 类型 0：商品；1：任务
     * @param size 数量
     * @param search 筛选信息
     * @return 获取的commodities列表
     */
    public List<CommodityEntity> getSelectedList(int type, int size, String search) {
        List<CommodityEntity> list = commodityDao.getAllCommodityByCondition(search, type);
        // 随机排序
        Collections.shuffle(list);
        return list.subList(0, min(size, list.size()));
    }
}
