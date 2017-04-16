package com.luckyhua.demo.global.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author luckyhua
 * @date 2016/11/25
 * @description Aspect for logging execution of service and repository Spring components.
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 开始请求时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();


    @Pointcut("execution(public * com.liegou.game.controller..*.*(..))")
    public void logPointcut(){}

    @Before("logPointcut()")
    public void doBefore(JoinPoint joinPoint){

        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("requestUrl = {}, requestMethod = {} ", request.getRequestURL().toString(), request.getMethod());
        log.info("Enter : {}.{}() with args = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning("logPointcut()")
    public void  doAfterReturning(JoinPoint joinPoint){
        // 处理完请求，返回内容
        log.info("耗时 : {} ms", (System.currentTimeMillis() - startTime.get()));
    }

}

