package com.oo.interceptor.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @ClassName
 * @Description TODO
 * @Author zhaoyuanyuan-ds
 * @Date 2018/8/6 11:24
 * @Version 1.0
 **/
@Aspect
@Component
@Order(1)
public class AspectDemo {
    private Logger logger = LoggerFactory.getLogger(getClass());
    ThreadLocal <Long> startTime=new ThreadLocal<>();
    @Pointcut("execution(public * com.oo.interceptor.controller.*.* (..))")
    private void controllerAspect(){}
    @Before(value ="controllerAspect()")
    public void methodBefor(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
        logger.info("==========请求内容");
        logger.info(joinPoint.getSignature().getDeclaringType() + ",method:" + joinPoint.getSignature().getName()
                + ",params:" + Arrays.asList(joinPoint.getArgs()));
        logger.info("===============请求内容===============");
    }
    @AfterReturning(returning = "o",pointcut = "controllerAspect()")
    public void methodAfterReturing(Object o ){
        logger.info("--------------返回内容----------------");
        logger.info("Response内容:"+ JSONObject.toJSONString(o));
        logger.info("--------------返回内容----------------");
        logger.info("请求处理时间为:"+(System.currentTimeMillis() - startTime.get()));
    }
    @AfterThrowing(pointcut = "controllerAspect()",throwing = "error")
    public void afterThrowing(JoinPoint jp, Throwable error) {
        System.out.println("error:" + error);
    }
}

