package com.example.echarge.dao;

import com.example.echarge.entity.CommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommodityDao extends JpaRepository<CommodityEntity, String> {
    public List<CommodityEntity> findAllByType(int type);
    @Query(value = "SELECT * FROM commodity AS c WHERE (locate(?1, c.title) > 0 OR locate(?1, c.tags) > 0) AND c.type = ?2 AND c.state = 0", nativeQuery = true)
    public List<CommodityEntity> getAllCommodityByCondition(String search, int type);
}
