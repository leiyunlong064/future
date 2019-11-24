package com.sand.common.entity;

import com.sand.common.enums.UserStatus;

import java.util.Date;

public class SystemUser {
    private Long userId;
    private String name;
    private String mobile;
    private String password;
    private UserStatus status;
    private Date createTime;
    private Date updateTime;

    public Long getUserId() {
        return userId;
    }

    public SystemUser setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public SystemUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public SystemUser setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SystemUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserStatus getStatus() {
        return status;
    }

    public SystemUser setStatus(UserStatus status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public SystemUser setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public SystemUser setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
