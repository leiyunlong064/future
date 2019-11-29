package com.sand.biz.system;

import com.sand.common.entity.RolePermission;
import com.sand.core.system.mappers.RolePermissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionService {
    private Logger logger = LoggerFactory.getLogger(RolePermissionService.class);

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public Long create(RolePermission rolePermission) {
        if (rolePermissionMapper.save(rolePermission) != 1) {
            throw new RuntimeException("create RolePermission error");
        }
        return rolePermission.getRolePermissionId();
    }

    public Long update(RolePermission rolePermission) {
        if (rolePermissionMapper.update(rolePermission) != 1) {
            throw new RuntimeException("update RolePermission error");
        }
        return rolePermission.getRolePermissionId();
    }

    public void delete(Long userRoleId) {
        if (rolePermissionMapper.delete(userRoleId) != 1) {
            throw new RuntimeException("delete RolePermission error");
        }
    }

    public RolePermission getRolePermission(Long userRoleId) {
        return rolePermissionMapper.getRolePermissionById(userRoleId);
    }

    public List<RolePermission> getRolePermissionList(Long roleId) {
        return rolePermissionMapper.getRolePermissionsByRoleId(roleId);
    }
}
