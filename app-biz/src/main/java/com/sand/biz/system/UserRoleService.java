package com.sand.biz.system;

import com.sand.common.entity.UserRole;
import com.sand.core.system.mappers.UserRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    private Logger logger = LoggerFactory.getLogger(UserRoleService.class);

    @Autowired
    private UserRoleMapper userRoleMapper;

    public Long create(UserRole userRole) {
        if (userRoleMapper.save(userRole) != 1) {
            throw new RuntimeException("create useRole error");
        }
        return userRole.getUserRoleId();
    }

    public Long update(UserRole userRole) {
        if (userRoleMapper.update(userRole) != 1) {
            throw new RuntimeException("update useRole error");
        }
        return userRole.getUserId();
    }

    public void delete(Long userRoleId) {
        if (userRoleMapper.delete(userRoleId) != 1) {
            throw new RuntimeException("delete useRole error");
        }
    }

    public UserRole getUserRole(Long userRoleId) {
        return userRoleMapper.getUserRoleById(userRoleId);
    }

    public List<UserRole> getUserRoles(Long userId) {
        return userRoleMapper.getUserRolesByUserId(userId);
    }
}
