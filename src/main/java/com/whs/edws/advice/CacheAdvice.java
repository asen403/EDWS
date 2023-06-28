package com.whs.edws.advice;


import com.whs.edws.annotation.Cache;
import com.whs.edws.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

@Component
@Aspect
@Slf4j
public class CacheAdvice {

    private final RedisUtil redisUtil;

    public CacheAdvice(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Pointcut("@annotation(com.whs.edws.annotation.Cache)")
    public void pointCut(){}


    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 从切入点获取方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 获取方法上的Cache注解相关值
        Cache cache = method.getAnnotation(Cache.class);
        // 获取过期时间，默认为0即永不过期
        int ttl = cache.ttl();
        // 获取key的前缀
        String prefix = cache.prefix();
        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        // 使用前缀+方法参数拼接key
        String key = String.format("nqdj:%s:%s", prefix, Arrays.toString(args));
        // 查询缓存中是否有相关key
        Object result = redisUtil.get(key);
        if(Objects.isNull(result)){
            log.info("缓存没有命中，查询数据库");
            result = joinPoint.proceed();
            redisUtil.set(key, result, ttl);
            return result;
        }
        log.info("缓存命中，直接返回");
        return result;
    }
}
