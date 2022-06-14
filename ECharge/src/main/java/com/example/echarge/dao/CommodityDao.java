package com.example.echarge.dao;

import com.example.echarge.entity.CommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommodityDao extends JpaRepository<CommodityEntity, Integer> {
    List<CommodityEntity> findAllByTypeAndState(int type, int state);
    @Query(value = "SELECT * FROM commodity AS c WHERE (locate(?1, c.title) > 0 OR locate(?1, c.tags) > 0) AND c.type = ?2 AND c.state = 0", nativeQuery = true)
    List<CommodityEntity> getAllCommodityByCondition(String search, int type);
    CommodityEntity findByItemId(int itemId);
    CommodityEntity findByItemIdAndState(int itemId, int state);
    List<CommodityEntity> findAllByPubIdAndType(int uid, int type);
    CommodityEntity findByPubId(int pub_id);
    CommodityEntity findByPrice(double price);
    CommodityEntity findByTitle(String title);
    void deleteByItemId(int itemId);
    List<CommodityEntity> findByState(int state);
}
