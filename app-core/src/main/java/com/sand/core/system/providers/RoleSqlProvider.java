package com.sand.core.system.providers;

import com.sand.common.entity.Role;
import com.sand.core.repositories.SQL;

/**
 * @description
 * @author: YunLong
 * @create: 2019-11-26
 **/
public class RoleSqlProvider {
    private String getEntityTable(){
        return "T_PERMISSION";
    }

    public String save(Role role) {
        return new SQL()
                .INSERT_INTO(getEntityTable())
                .VALUES("ROLE_NAME", "#{roleName}")
                .VALUES("ROLE_TYPE", "#{roleType}")
                .VALUES("STATUS", "#{status}")
                .VALUES_IF("CREATE_TIME", "#{createTime}", role.getCreateTime() != null)
                .VALUES_IF("UPDATE_TIME", "#{updateTime}", role.getUpdateTime() != null)
                .toString();
    }

    public String update(Role role) {
        return new SQL()
                .UPDATE(getEntityTable())
                .SET_IF("ROLE_NAME = #{roleName}", role.getRoleName() != null)
                .SET_IF("ROLE_TYPE = #{roleType}", role.getRoleType() != null)
                .SET_IF("STATUS = #{status}", role.getStatus() != null)
                .WHERE("ROLE_ID = #{roleId}")
                .toString();
    }

    public String delete() {
        return new SQL()
                .DELETE_FROM(getEntityTable())
                .WHERE("ROLE_ID = #{roleId}")
                .toString();
    }

    public String findOne() {
        return new SQL()
                .SELECT("*")
                .FROM(getEntityTable())
                .WHERE("ROLE_ID = #{roleId}")
                .toString();
    }
}
