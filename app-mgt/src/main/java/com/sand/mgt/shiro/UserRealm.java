package com.sand.mgt.shiro;

import com.sand.biz.exception.LoginException;
import com.sand.biz.system.AuthService;
import com.sand.biz.system.UserService;
import com.sand.common.entity.User;
import com.sand.common.enums.UserStatus;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class UserRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        String password = (String)token.getCredentials();
        if(StringUtils.isEmpty(username)){
            throw new LoginException("username is null");
        }
        if(StringUtils.isEmpty(password)){
            throw  new LoginException("password is null");
        }

        User user = userService.getUserByMobile(username);

        if(user == null){
            throw new UnknownAccountException("user not exist");
        }

        if(!authService.checkPassword(password, user)){
            throw new IncorrectCredentialsException("password is wrong");
        }
        if(user.getStatus() != UserStatus.ACTIVE){
            throw new LockedAccountException("account is locked");
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }
}
