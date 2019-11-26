package com.sand.common.entity;

import java.util.Date;

/**
 * @description
 * @author: YunLong
 * @create: 2019-11-26
 **/
public class RolePermission {
    private Long rolePermissionId;
    private Long roleId;
    private Long permissionId;
    private Date createTime;
    private Date updateTime;

    public Long getRolePermissionId() {
        return rolePermissionId;
    }

    public RolePermission setRolePermissionId(Long rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
        return this;
    }

    public Long getRoleId() {
        return roleId;
    }

    public RolePermission setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public RolePermission setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public RolePermission setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public RolePermission setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
