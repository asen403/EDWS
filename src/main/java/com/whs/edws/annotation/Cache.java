package com.whs.edws.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

    // key的前缀
    String prefix();

    // 过期时间
    int ttl() default 0;
}
