package com.example.echarge.service;

import com.example.echarge.dao.CommentDao;
import com.example.echarge.dao.CommodityDao;
import com.example.echarge.dao.TransactionDao;
import com.example.echarge.dao.UserDao;
import com.example.echarge.entity.CommentEntity;
import com.example.echarge.entity.CommodityEntity;
import com.example.echarge.entity.TransactionEntity;
import com.example.echarge.entity.UserEntity;
import com.example.echarge.util.getCoverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.LinkedList;

@Service
public class TransactionService {
    @Autowired
    UserDao userDao;
    @Autowired
    TransactionDao transactionDao;
    @Autowired
    CommodityDao commodityDao;
    @Autowired
    CommentDao commentDao;

    public LinkedList<Object> getTransactionListByStateAndIndex(int uid, int state, int index, int isCustomer){
        LinkedList<Object> res = new LinkedList<>();
        LinkedList<TransactionEntity> trans = new LinkedList<>();
//        CommodityEntity commodity = commodityDao.getById(commodityId);
        if(state != -1){
            if(isCustomer == 1) trans = transactionDao.findByStateAndCustomerIdAndTransactionIdGreaterThan(state, uid, index);
            else trans = transactionDao.findByStateAndSellerIdAndTransactionIdGreaterThan(state, uid, index);
        }
        else{
            if(isCustomer == 1) trans = transactionDao.findByCustomerIdAndTransactionIdGreaterThan(uid, index);
            else trans = transactionDao.findBySellerIdAndTransactionIdGreaterThan(uid, index);
        }
        int num = 0;
        for(TransactionEntity item : trans){
            if(++num>5){
                break;
            }
            LinkedHashMap<String, Object> tmp = new LinkedHashMap<>();
            CommodityEntity commodity = commodityDao.getById(item.getItemId());
            tmp.put("transaction_id", item.getTransactionId());
            tmp.put("deal_time", item.getDealTime());
            tmp.put("type", item.getType());
            tmp.put("state", item.getState());
            tmp.put("figure_url", getCoverUtil.getCover(commodity.getFigureUrls()));
            tmp.put("title", commodity.getTitle());
            tmp.put("price", commodity.getPrice());
            res.add(tmp);
        }
        return res;
    }
    public LinkedList<Object> getTransactionInfo(int uid){
        LinkedList<Object> res = new LinkedList<>();
        for(int i=1; i<=5; i++){
            LinkedHashMap<String, Object> tmp = new LinkedHashMap<>();
            int num = transactionDao.findByStateAndCustomerId(i, uid).size();
            tmp.put("state", i);
            tmp.put("num", num);
            res.add(tmp);
//            System.out.println(num);
        }
        return res;
    }

    public void addComment(CommentEntity comment){
        commentDao.saveAndFlush(comment);
    }

    public TransactionEntity addTrans(TransactionEntity trans) {
        return transactionDao.saveAndFlush(trans);
    }
    public void updateTrans(TransactionEntity trans) { transactionDao.saveAndFlush(trans); }

    public TransactionEntity getPayableTrans(int transId) {
        return transactionDao.findByTransactionIdAndState(transId, 1);
    }

    public TransactionEntity getTransByState(int transId, int state) {
        return transactionDao.findByTransactionIdAndState(transId, state);
    }
    
    public LinkedHashMap<String, Object> getTransactionDetail(int transactionID){
        TransactionEntity transaction = transactionDao.getById(transactionID);
        CommodityEntity commodity = commodityDao.getById(transaction.getItemId());
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        res.put("commodityID", commodity.getItemId());
        res.put("coverUrl", getCoverUtil.getCover(commodity.getFigureUrls()));
        res.put("title", commodity.getTitle());
        res.put("price", commodity.getPrice());
        res.put("transTime", transaction.getDealTime());
        res.put("sellerID", transaction.getSellerId());
        res.put("place", commodity.getPlace()==null?"":commodity.getPlace());
        res.put("timeExcepted", commodity.getTimeExpected()==null?"":commodity.getTimeExpected());
        res.put("state", transaction.getState());
        res.put("type", transaction.getType());
        UserEntity seller = userDao.findByUid(transaction.getSellerId());
        res.put("sellerName", seller.getName());
        return res;
    }
}
