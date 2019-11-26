package com.sand.core.system.providers;

import com.sand.common.entity.RolePermission;
import com.sand.core.repositories.SQL;

/**
 * @description
 * @author: YunLong
 * @create: 2019-11-26
 **/
public class RolePermissionSqlProvider {
    private String getEntityTable(){
        return "T_PERMISSION";
    }

    public String save(RolePermission rolePermission) {
        return new SQL()
                .INSERT_INTO(getEntityTable())
                .VALUES("ROLE_ID", "#{roleId}")
                .VALUES("PERMISSION_ID", "#{permissionId}")
                .VALUES_IF("CREATE_TIME", "#{createTime}", rolePermission.getCreateTime() != null)
                .VALUES_IF("UPDATE_TIME", "#{updateTime}", rolePermission.getUpdateTime() != null)
                .toString();
    }

    public String update(RolePermission rolePermission) {
        return new SQL()
                .UPDATE(getEntityTable())
                .SET_IF("ROLE_ID = #{roleId}", rolePermission.getRoleId() != null)
                .SET_IF("PERMISSION_ID = #{permissionId}", rolePermission.getPermissionId() != null)
                .WHERE("ROLE_PERMISSION_ID = #{rolePermissionId}")
                .toString();
    }

    public String delete() {
        return new SQL()
                .DELETE_FROM(getEntityTable())
                .WHERE("ROLE_PERMISSION_ID = #{rolePermissionId}")
                .toString();
    }

    public String findOne() {
        return new SQL()
                .SELECT("*")
                .FROM(getEntityTable())
                .WHERE("ROLE_PERMISSION_ID = #{rolePermissionId}")
                .toString();
    }

    public String findByRoleId(){
        return new SQL()
                .SELECT("*")
                .FROM(getEntityTable())
                .WHERE("ROLE_ID = #{roleId}")
                .toString();
    }
}
