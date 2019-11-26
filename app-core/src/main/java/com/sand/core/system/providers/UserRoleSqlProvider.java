package com.sand.core.system.providers;

import com.sand.common.entity.UserRole;
import com.sand.core.repositories.SQL;

/**
 * @description
 * @author: YunLong
 * @create: 2019-11-26
 **/
public class UserRoleSqlProvider {
    private String getEntityTable(){
        return "T_PERMISSION";
    }

    public String save(UserRole userRole) {
        return new SQL()
                .INSERT_INTO(getEntityTable())
                .VALUES("USER_IUD", "#{userId}")
                .VALUES("ROLE_ID", "#{roleId}")
                .VALUES_IF("CREATE_TIME", "#{createTime}", userRole.getCreateTime() != null)
                .VALUES_IF("UPDATE_TIME", "#{updateTime}", userRole.getUpdateTime() != null)
                .toString();
    }

    public String update(UserRole userRole) {
        return new SQL()
                .UPDATE(getEntityTable())
                .SET_IF("USER_IUD = #{userId}", userRole.getUserId() != null)
                .SET_IF("ROLE_ID = #{roleId}", userRole.getRoleId() != null)
                .WHERE("USER_ROLE_ID = #{userRoleId}")
                .toString();
    }

    public String delete() {
        return new SQL()
                .DELETE_FROM(getEntityTable())
                .WHERE("USER_ROLE_ID = #{userRoleId}")
                .toString();
    }

    public String findOne() {
        return new SQL()
                .SELECT("*")
                .FROM(getEntityTable())
                .WHERE("USER_ROLE_ID = #{userRoleId}")
                .toString();
    }

    public String findByUserId(){
        return new SQL()
                .SELECT("*")
                .FROM(getEntityTable())
                .WHERE("USER_ID = #{userId}")
                .toString();
    }
}
