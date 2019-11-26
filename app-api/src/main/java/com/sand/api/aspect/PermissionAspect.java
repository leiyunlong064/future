package com.sand.api.aspect;

import com.sand.api.annotation.CheckPermission;
import com.sand.biz.exception.PermissionException;
import com.sand.biz.system.PermissionService;
import com.sand.common.enums.PermissionType;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PermissionAspect {
    private final Logger logger = LoggerFactory.getLogger(PermissionAspect.class);

    @Autowired
    private PermissionService permissionService;

    @Pointcut(value = "@annotation(checkPermission)")
    public void permissionPointcut(CheckPermission checkPermission){}

    @Before(value = "permissionPointcut(checkPermission)", argNames = "checkPermission")
    public void checkPermission(CheckPermission checkPermission){
        //todo get userId
        Long userId = 0L;
        PermissionType permissionType = checkPermission.permissionType();
        logger.info("check permission,userId:{},type:{}",userId,permissionType);
        if(!permissionService.checkPermission(userId, permissionType)){
            throw new PermissionException("no permission");
        }

    }
}
