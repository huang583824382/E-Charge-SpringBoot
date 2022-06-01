package com.example.echarge.dao;

import com.example.echarge.entity.CommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommodityDao extends JpaRepository<CommodityEntity, Integer> {
    public List<CommodityEntity> findAllByTypeAndState(int type, int state);
    @Query(value = "SELECT * FROM commodity AS c WHERE (locate(?1, c.title) > 0 OR locate(?1, c.tags) > 0) AND c.type = ?2 AND c.state = 0", nativeQuery = true)
    public List<CommodityEntity> getAllCommodityByCondition(String search, int type);
    public CommodityEntity findByItemId(int itemId);
    public CommodityEntity findByItemIdAndState(int itemId, int state);
    public List<CommodityEntity> findAllByPubIdAndType(int uid, int type);
    public List<CommodityEntity> findByState(int state);
}
