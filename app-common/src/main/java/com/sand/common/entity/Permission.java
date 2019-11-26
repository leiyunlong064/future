package com.sand.common.entity;

import com.sand.common.enums.ActiveStatus;
import com.sand.common.enums.PermissionType;

import java.util.Date;

/**
 * @description
 * @author: YunLong
 * @create: 2019-11-26
 **/
public class Permission {
    private Long permissionId;
    private String permissionName;
    private PermissionType permissionType;
    private ActiveStatus status;
    private Date createTime;
    private Date updateTime;

    public Long getPermissionId() {
        return permissionId;
    }

    public Permission setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
        return this;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public Permission setPermissionName(String permissionName) {
        this.permissionName = permissionName;
        return this;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public Permission setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
        return this;
    }

    public ActiveStatus getStatus() {
        return status;
    }

    public Permission setStatus(ActiveStatus status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Permission setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Permission setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
