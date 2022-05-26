/**
 * 文 件 名 : ListService.java
 * 描 述 : 主页和搜索展示列表对应的服务
 * 最后修改时间 : 2022-5-18 12:18 am
 */

package com.example.echarge.service;

import com.example.echarge.dao.CommodityDao;
import com.example.echarge.dao.UserDao;
import com.example.echarge.entity.CommodityEntity;
import com.example.echarge.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.*;

import static com.example.echarge.util.getCoverUtil.getCover;
import static java.lang.Math.min;

@Service
public class ListService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    CommodityDao commodityDao;

    @Autowired
    UserDao userDao;

    public LinkedHashMap<String, Object> assembleRes(int type, List<CommodityEntity> list) {
        LinkedHashMap<String, Object> res = new LinkedHashMap<String, Object>(0);
        if(type == 1) {
            List<UserEntity> uList = new ArrayList<>();
            for (CommodityEntity commodityEntity : list) {
                commodityEntity.setFigureUrls(getCover(commodityEntity.getFigureUrls()));
                uList.add(userDao.findByUid(commodityEntity.getPubId()));
            }
            res.put("pubUser", uList);
        }
        else {
            for (CommodityEntity commodityEntity : list) {
                commodityEntity.setFigureUrls(getCover(commodityEntity.getFigureUrls()));
            }
        }
        res.put("list", list);
        return res;
    }

    /**
     * 无筛选条件随机获取commodities
     * @param type 类型 0：商品；1：任务
     * @param size 数量
     * @return 商品：{list: 商品列表}; 任务：{pubUser: 任务发布者列表; list: 商品列表}
     */
    public LinkedHashMap<String, Object> getList(int type, int size, int state) {
        List<CommodityEntity> list = commodityDao.findAllByTypeAndState(type, 0);
        // 随机排序
        Collections.shuffle(list);
        return assembleRes(type, list.subList(0, min(size, list.size())));
    }

    // id 降序，也即时间降序
    static Comparator<CommodityEntity> id = new Comparator<CommodityEntity>() {
        @Override
        public int compare(CommodityEntity o1, CommodityEntity o2) {
            return o2.getItemId() - o1.getItemId();
        }
    };

    // 价格降序
    static Comparator<CommodityEntity> priceDesc = new Comparator<CommodityEntity>() {
        @Override
        public int compare(CommodityEntity o1, CommodityEntity o2) {
            return Double.compare(o1.getItemId(), o2.getItemId());
        }
    };

    // 价格升序
    static Comparator<CommodityEntity> priceAsc = new Comparator<CommodityEntity>() {
        @Override
        public int compare(CommodityEntity o1, CommodityEntity o2) {
            return Double.compare(o2.getItemId(), o1.getItemId());
        }
    };

    /**
     * 有筛选条件随机获取commodities，在标题和标签中查找对应字段
     * @param type 类型 0：商品；1：任务
     * @param size 数量
     * @param search 筛选信息
     * @param order 排序方式 1：升序；2：降序；3：时间降序
     * @param lastId 上次最后的的Id，用于分隔
     * @return {list: commodities列表; totalCount: 符合条件的commodities总数量}
     */
    public LinkedHashMap<String, Object> getSelectedList(int type, int size, String search, int order, int lastId) {
        LinkedHashMap<String, Object> res = new LinkedHashMap<String, Object>(0);
        List<CommodityEntity> list = commodityDao.getAllCommodityByCondition(search, type);

        // 列表排序
        switch (order) {
            case 1: list.sort(priceAsc); break;
            case 2: list.sort(priceDesc); break;
            case 3: list.sort(id); break;
        }
        int startPos = 0;
        // 接着上次查询的范围
        if(lastId != -1) {
            Optional<CommodityEntity> commodityOp = list.stream().filter(commodity -> commodity.getItemId() == lastId).findFirst();
            if(commodityOp.isPresent()) {
                startPos = list.indexOf(commodityOp.get());
                if (startPos + 1 < list.size()) {
                    startPos++;
                }
            }
        }
        // 有效的起始位置，返回对应的数据
        if(startPos < list.size()) {
            List<CommodityEntity> resList = list.subList(startPos, min(startPos+size, list.size()));
            res = assembleRes(type, resList);
            res.put("totalCount", list.size());
        }
        System.out.println(res);
        return res;
    }
}
