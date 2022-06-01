package com.example.echarge.dao;

import com.example.echarge.entity.CommentEntity;
import com.example.echarge.entity.CommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.stream.events.Comment;
import java.util.LinkedList;

public interface CommentDao extends JpaRepository<CommentEntity, Integer> {
}
