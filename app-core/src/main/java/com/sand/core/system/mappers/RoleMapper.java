package com.sand.core.system.mappers;

import com.sand.common.entity.Role;
import com.sand.core.system.providers.RoleSqlProvider;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RoleMapper {
    @InsertProvider(type = RoleSqlProvider.class, method = "save")
    @Options(useGeneratedKeys = true)
    int save(Role role);

    @UpdateProvider(type = RoleSqlProvider.class, method = "update")
    int update(Role role);

    @DeleteProvider(type = RoleSqlProvider.class, method = "delete")
    int delete(@Param("roleId") Long roleId);

    @SelectProvider(type = RoleSqlProvider.class, method = "findOne")
    Role getPermissionById(@Param("roleId") Long roleId);
}
