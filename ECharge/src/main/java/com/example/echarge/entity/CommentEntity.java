package com.example.echarge.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment", schema = "echarge", catalog = "")
public class CommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "comment_id")
    private int commentId;
    @Basic
    @Column(name = "transaction_id")
    private int transactionId;
    @Basic
    @Column(name = "uid")
    private Integer uid;
    @Basic
    @Column(name = "rate")
    private Double rate;
    @Basic
    @Column(name = "comment")
    private String comment;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentEntity comment1 = (CommentEntity) o;

        if (commentId != comment1.commentId) return false;
        if (transactionId != comment1.transactionId) return false;
        if (uid != null ? !uid.equals(comment1.uid) : comment1.uid != null) return false;
        if (rate != null ? !rate.equals(comment1.rate) : comment1.rate != null) return false;
        if (comment != null ? !comment.equals(comment1.comment) : comment1.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + transactionId;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
