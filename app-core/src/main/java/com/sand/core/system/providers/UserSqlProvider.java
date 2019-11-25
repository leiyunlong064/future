package com.sand.core.system.providers;

import com.sand.common.entity.User;
import com.sand.core.repositories.SQL;
import org.springframework.util.StringUtils;

public class UserSqlProvider {
    private String getEntityTable() {
        return "T_USER";
    }

    public String save(User user) {
        return new SQL()
                .INSERT_INTO(getEntityTable())
                .VALUES("USER_ID", "#{userId}")
                .VALUES("NAME", "#{name}")
                .VALUES("MOBILE", "#{mobile}")
                .VALUES("PASSWORD", "#{password}")
                .VALUES_IF("STATUS", "#{status}", user.getStatus() != null)
                .VALUES_IF("CREATED_TIME", "#{createTime}", user.getCreateTime() != null)
                .VALUES_IF("UPDATE_TIME", "#{updateTime}", user.getUpdateTime() != null)
                .toString();
    }

    public String update(User user) {
        return new SQL()
                .UPDATE(getEntityTable())
                .SET_IF("NAME = #{name}", !StringUtils.isEmpty(user.getName()))
                .SET_IF("MOBILE = #{mobile}", !StringUtils.isEmpty(user.getMobile()))
                .SET_IF("PASSWORD = #{password}", !StringUtils.isEmpty(user.getPassword()))
                .SET_IF("STATUS = #{status}", user.getStatus() != null)
                .WHERE("USER_ID = #{userId}")
                .toString();
    }

    public String findOne() {
        return new SQL()
                .SELECT("*")
                .FROM(getEntityTable())
                .WHERE("USER_ID = #{userId}")
                .toString();
    }

    public String findOneByMobile() {
        return new SQL()
                .SELECT("*")
                .FROM(getEntityTable())
                .WHERE("MOBILE = #{mobile}")
                .toString();
    }

    public String findList(User user) {
        return new SQL()
                .SELECT("*")
                .FROM(getEntityTable())
                .WHERE_IF("NAME = #{name}", user.getName() != null)
                .WHERE_IF("MOBILE = #{mobile}", user.getMobile() != null)
                .WHERE_IF("STATUS = #{status}", user.getStatus() != null)
                .WHERE("IS_DELETED = 'N'")
                .ORDER_BY("CREATE_TIME DESC")
                .toString();
    }
}
