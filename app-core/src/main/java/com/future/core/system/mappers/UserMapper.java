package com.future.core.system.providers;

import com.future.common.entity.SystemUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @InsertProvider(type = UserSqlProvider.class, method = "save")
    @Options(useGeneratedKeys = true)
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyColumn = "userId", keyProperty = "userId", before = false, resultType = Long.class)
    int save(SystemUser user);

    @UpdateProvider(type = UserSqlProvider.class, method = "update")
    int update(SystemUser user);

    @DeleteProvider(type = UserSqlProvider.class, method = "delete")
    int delete(@Param("userId") Long userId);

    @SelectProvider(type = UserSqlProvider.class, method = "findOne")
    SystemUser findOne(@Param("userId") Long userId);

    @SelectProvider(type = UserSqlProvider.class, method = "findOneByMobile")
    SystemUser findOneByMobile(@Param("mobile") String mobile);

    @SelectProvider(type = UserSqlProvider.class, method = "findList")
    List<SystemUser> findList(SystemUser user);
}
