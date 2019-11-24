package com.sand.core.system.mappers;

import com.sand.common.entity.SystemUser;
import com.sand.core.system.providers.UserSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @InsertProvider(type = UserSqlProvider.class, method = "save")
    @Options(useGeneratedKeys = true, keyColumn = "userId", keyProperty = "userId")
    int save(SystemUser user);

    @UpdateProvider(type = UserSqlProvider.class, method = "update")
    int update(SystemUser user);

    @SelectProvider(type = UserSqlProvider.class, method = "findOne")
    SystemUser findOne(@Param("userId") Long userId);

    @SelectProvider(type = UserSqlProvider.class, method = "findOneByMobile")
    SystemUser findOneByMobile(@Param("mobile") String mobile);

    @SelectProvider(type = UserSqlProvider.class, method = "findList")
    List<SystemUser> findList(SystemUser user);
}
