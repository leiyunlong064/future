package com.sand.core.system.mappers;

import com.sand.common.entity.RolePermission;
import com.sand.core.system.providers.RolePermissionSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RolePermissionMapper {
    @InsertProvider(type = RolePermissionSqlProvider.class, method = "save")
    @Options(useGeneratedKeys = true)
    int save(RolePermission rolePermission);

    @UpdateProvider(type = RolePermissionSqlProvider.class, method = "update")
    int update(RolePermission rolePermission);

    @DeleteProvider(type = RolePermissionSqlProvider.class, method = "delete")
    int delete(@Param("rolePermissionId") Long rolePermissionId);

    @SelectProvider(type = RolePermissionSqlProvider.class, method = "findOne")
    RolePermission getRolePermissionById(@Param("rolePermissionId") Long rolePermissionId);

    @SelectProvider(type = RolePermissionSqlProvider.class, method = "findByRoleId")
    List<RolePermission> getRolePermissionsByRoleId(@Param("roleId") Long roleId);
}
