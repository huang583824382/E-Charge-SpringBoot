package com.example.echarge.dao;

import com.example.echarge.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportDao extends JpaRepository<ReportEntity, Integer> {
    List<ReportEntity> findByState(int state);

    ReportEntity findByReportId(int id);
}
