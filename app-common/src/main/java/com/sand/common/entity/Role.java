package com.sand.common.entity;


import com.sand.common.enums.ActiveStatus;
import com.sand.common.enums.RoleType;

import java.util.Date;

/**
 * @description
 * @author: YunLong
 * @create: 2019-11-26
 **/
public class Role {
    private Long roleId;
    private String roleName;
    private RoleType roleType;
    private ActiveStatus status;
    private Date createTime;
    private Date updateTime;

    public Long getRoleId() {
        return roleId;
    }

    public Role setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public Role setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public Role setRoleType(RoleType roleType) {
        this.roleType = roleType;
        return this;
    }

    public ActiveStatus getStatus() {
        return status;
    }

    public Role setStatus(ActiveStatus status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Role setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Role setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
