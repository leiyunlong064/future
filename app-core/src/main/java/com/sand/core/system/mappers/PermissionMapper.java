package com.sand.core.system.mappers;

import com.sand.common.entity.Permission;
import com.sand.core.system.providers.PermissionSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermissionMapper {
    @InsertProvider(type = PermissionSqlProvider.class, method = "save")
    @Options(useGeneratedKeys = true)
    int save(Permission permission);

    @UpdateProvider(type = PermissionSqlProvider.class, method = "update")
    int update(Permission permission);

    @DeleteProvider(type = PermissionSqlProvider.class, method = "delete")
    int delete(@Param("permissionId") Long permissionId);

    @SelectProvider(type = PermissionSqlProvider.class, method = "findOne")
    Permission getPermissionById(@Param("permissionId") Long permissionId);

    @SelectProvider(type = PermissionSqlProvider.class, method = "findPermissionsByUserId")
    List<Permission> getPermissions(@Param("userId") Long userId);
}
