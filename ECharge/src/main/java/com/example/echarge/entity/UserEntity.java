package com.example.echarge.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "user", schema = "e-change", catalog = "")
public class UserEntity {
    @Basic
    @Column(name = "uid")
    private int uid;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "wx_id")
    private String wxId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "birthday")
    private Date birthday;
    @Basic
    @Column(name = "gender")
    private Integer gender;
    @Basic
    @Column(name = "is_login")
    private Integer isLogin;
    @Basic
    @Column(name = "token")
    private String token;
    @Basic
    @Column(name = "token_expire")
    private Timestamp tokenExpire;
    @Basic
    @Column(name = "last_op_time")
    private Timestamp lastOpTime;
    @Basic
    @Column(name = "credit")
    private Integer credit;
    @Basic
    @Column(name = "balance")
    private Double balance;
    @Basic
    @Column(name = "icon_url")
    private String iconUrl;
    @Basic
    @Column(name = "is_admin")
    private Integer isAdmin;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Integer isLogin) {
        this.isLogin = isLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(Timestamp tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public Timestamp getLastOpTime() {
        return lastOpTime;
    }

    public void setLastOpTime(Timestamp lastOpTime) {
        this.lastOpTime = lastOpTime;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (uid != that.uid) return false;
        if (wxId != null ? !wxId.equals(that.wxId) : that.wxId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (isLogin != null ? !isLogin.equals(that.isLogin) : that.isLogin != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (tokenExpire != null ? !tokenExpire.equals(that.tokenExpire) : that.tokenExpire != null) return false;
        if (lastOpTime != null ? !lastOpTime.equals(that.lastOpTime) : that.lastOpTime != null) return false;
        if (credit != null ? !credit.equals(that.credit) : that.credit != null) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        if (iconUrl != null ? !iconUrl.equals(that.iconUrl) : that.iconUrl != null) return false;
        if (isAdmin != null ? !isAdmin.equals(that.isAdmin) : that.isAdmin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (wxId != null ? wxId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (isLogin != null ? isLogin.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (tokenExpire != null ? tokenExpire.hashCode() : 0);
        result = 31 * result + (lastOpTime != null ? lastOpTime.hashCode() : 0);
        result = 31 * result + (credit != null ? credit.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (iconUrl != null ? iconUrl.hashCode() : 0);
        result = 31 * result + (isAdmin != null ? isAdmin.hashCode() : 0);
        return result;
    }
}
