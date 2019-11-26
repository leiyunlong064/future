package com.sand.biz.system;

import com.sand.common.entity.Permission;
import com.sand.common.enums.PermissionType;
import com.sand.core.system.mappers.PermissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionService {
    private final Logger logger = LoggerFactory.getLogger(PermissionService.class);

    @Autowired
    private PermissionMapper permissionMapper;

    public boolean checkPermission(Long userId, PermissionType permissionType) {
        logger.info("checkPermission,userId:{},type:{}", userId, permissionType);
        Set<PermissionType> set = getUserPermissionType(userId);
        return set.contains(permissionType);
    }

    private Set<PermissionType> getUserPermissionType(Long userId) {
        Set<PermissionType> permissionTypes = new HashSet<>();
        List<Permission> permissions = getPermissionsByUserId(userId);
        permissions.forEach(permission -> {
            permissionTypes.add(permission.getPermissionType());
        });

        return permissionTypes;
    }

    public List<Permission> getPermissionsByUserId(Long userId) {
        return permissionMapper.getPermissions(userId);
    }


}
