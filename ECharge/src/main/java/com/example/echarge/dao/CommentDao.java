package com.example.echarge.dao;

import com.example.echarge.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<CommentEntity, Integer> {
}
