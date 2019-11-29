package com.sand.core.system.mappers;

import com.sand.common.entity.UserRole;
import com.sand.core.system.providers.UserRoleSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    @InsertProvider(type = UserRoleSqlProvider.class, method = "save")
    @Options(useGeneratedKeys = true)
    int save(UserRole userRole);

    @UpdateProvider(type = UserRoleSqlProvider.class, method = "update")
    int update(UserRole userRole);

    @DeleteProvider(type = UserRoleSqlProvider.class, method = "delete")
    int delete(@Param("userRoleId") Long userRoleId);

    @SelectProvider(type = UserRoleSqlProvider.class, method = "findOne")
    UserRole getUserRoleById(@Param("userRoleId") Long userRoleId);

    @SelectProvider(type = UserRoleSqlProvider.class, method = "findByUserId")
    List<UserRole> getUserRolesByUserId(@Param("userId") Long userId);
}
