package com.example.echarge.dao;

import com.example.echarge.entity.CommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommodityDao extends JpaRepository<CommodityEntity, String> {
    public List<CommodityEntity> findAllByType(int type);
    @Query(value = "SELECT c FROM CommodityEntity c WHERE (locate(c.title, ?1) > 0 OR locate(c.tags, ?1) > 0) AND c.type = ?2", nativeQuery = true)
    public List<CommodityEntity> getAllCommodityByCondition(String search, int type);
}
