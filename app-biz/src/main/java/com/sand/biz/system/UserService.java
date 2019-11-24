package com.sand.biz.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sand.common.entity.SystemUser;
import com.sand.common.enums.UserStatus;
import com.sand.core.system.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public Long create(SystemUser user) {
        user.setStatus(UserStatus.ACTIVE);
        if (userMapper.save(user) != 1) {
            throw new RuntimeException("创建用户失败");
        }
        return user.getUserId();
    }

    public Long update(SystemUser user) {
        if (userMapper.update(user) != 1) {
            throw new RuntimeException("更新用户失败");
        }
        return user.getUserId();
    }

    public SystemUser getUser(Long userId) {
        return userMapper.findOne(userId);
    }

    public SystemUser getUserByMobile(String mobile) {
        return userMapper.findOneByMobile(mobile);
    }

    public PageInfo<SystemUser> getUserListByPage(SystemUser systemUser, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SystemUser> list = userMapper.findList(systemUser);
        PageInfo page = new PageInfo(list);
        return page;
    }

    public Long resetPassword(Long userId, String password) {
        SystemUser user = this.getUser(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new RuntimeException("用户未激活");
        }
        SystemUser systemUser = new SystemUser()
                .setUserId(userId)
                .setPassword(password);
        return this.update(systemUser);
    }
}
