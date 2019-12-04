package com.sand.api.aspect;

import com.sand.biz.collection.PageCollectionService;
import com.sand.common.collection.PageCollection;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Aspect
public class PageAspect {
    private final Logger logger = LoggerFactory.getLogger(PageAspect.class);

    @Autowired
    private PageCollectionService pageCollectionService;

    @Pointcut("execution(public * com.sand.api.*.controller..*.*(..))")
    public void collectPointcut(){}

    @Around("collectPointcut()")
    public Object collect(ProceedingJoinPoint joinPoint){
        logger.info("HttpAspect start");
        Object resp = null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        PageCollection pageCollection = new PageCollection();
        pageCollection.setUrl(request.getRequestURI())
                .setMethodName(request.getMethod())
                .setRemoteAddr(request.getRemoteAddr())
                .setReferer(request.getHeader("referer"))
        .setStartTime(new Date());

        //记录访问的类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());

        //记录传递的参数
        logger.info("args={}",joinPoint.getArgs());
        try {
            resp = joinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error("execute joinPoint error");
        }
        pageCollection.setEndTime(new Date());
        pageCollectionService.create(pageCollection);
        return resp;
    }
}
