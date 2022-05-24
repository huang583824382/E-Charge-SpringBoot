package com.example.echarge.service;

import com.example.echarge.dao.CommodityDao;
import com.example.echarge.entity.CommodityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
