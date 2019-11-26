package com.sand.core.system.providers;


import com.sand.common.entity.Permission;
import com.sand.core.repositories.SQL;

/**
 * @description
 * @author: YunLong
 * @create: 2019-11-26
 **/
public class PermissionSqlProvider {
    private String getEntityTable(){
        return "T_PERMISSION";
    }

    public String save(Permission permission) {
        return new SQL()
                .INSERT_INTO(getEntityTable())
                .VALUES("PERMISSION_NAME", "#{permissionName}")
                .VALUES("PERMISSION_TYPE", "#{permissionType}")
                .VALUES("STATUS", "#{status}")
                .VALUES_IF("CREATE_TIME", "#{createTime}", permission.getCreateTime() != null)
                .VALUES_IF("UPDATE_TIME", "#{updateTime}", permission.getUpdateTime() != null)
                .toString();
    }

    public String update(Permission permission) {
        return new SQL()
                .UPDATE(getEntityTable())
                .SET_IF("PERMISSION_NAME = #{permissionName}", permission.getPermissionName() != null)
                .SET_IF("PERMISSION_TYPE = #{permissionType}", permission.getPermissionType() != null)
                .SET_IF("STATUS = #{status}", permission.getStatus() != null)
                .WHERE("PERMISSION_ID = #{permissionId}")
                .toString();
    }

    public String delete() {
        return new SQL()
                .DELETE_FROM(getEntityTable())
                .WHERE("PERMISSION_ID = #{permissionId}")
                .toString();
    }

    public String findOne() {
        return new SQL()
                .SELECT("*")
                .FROM(getEntityTable())
                .WHERE("PERMISSION_ID = #{permissionId}")
                .toString();
    }

    public String findPermissionsByUserId() {
        return new SQL()
                .SELECT("perm.*")
                .FROM("T_USER_ROLE user ")
                .LEFT_JOIN("T_ROLE_PERMISSION role on user.ROLE_ID = role.ROLE_ID")
                .LEFT_JOIN("T_PERMISSION perm on role.PERMISSION_ID = perm.PERMISSION_ID")
                .WHERE("user.USER_ID = #{userId}")
                .toString();
    }
}
