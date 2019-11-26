package com.sand.common.entity;

import java.util.Date;

/**
 * @description
 * @author: YunLong
 * @create: 2019-11-26
 **/
public class UserRole {
    private Long userRoleId;
    private Long userId;
    private Long roleId;
    private Date createTime;
    private Date updateTime;

    public Long getUserRoleId() {
        return userRoleId;
    }

    public UserRole setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public UserRole setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getRoleId() {
        return roleId;
    }

    public UserRole setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UserRole setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public UserRole setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
