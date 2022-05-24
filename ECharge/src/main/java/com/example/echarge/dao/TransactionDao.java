package com.example.echarge.dao;

import com.example.echarge.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedList;

public interface TransactionDao extends JpaRepository<TransactionEntity, Integer> {
    LinkedList<TransactionEntity> findByStateAndCustomerId(Integer state, Integer customerId);
    LinkedList<TransactionEntity> findByStateAndCustomerIdAndTransactionIdGreaterThan(Integer state, Integer customerId, Integer transId);
    LinkedList<TransactionEntity> findByStateAndSellerIdAndTransactionIdGreaterThan(Integer state, Integer sellerId, Integer transId);

    LinkedList<TransactionEntity> findByCustomerIdAndTransactionIdGreaterThan(Integer customerId, Integer transId);
    LinkedList<TransactionEntity> findBySellerIdAndTransactionIdGreaterThan(Integer sellerId, Integer transId);

    TransactionEntity findByTransactionIdAndState(int transId, int state);

}
