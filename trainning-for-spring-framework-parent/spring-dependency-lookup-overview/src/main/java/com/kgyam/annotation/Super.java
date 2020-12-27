package com.kgyam.annotation;

import java.lang.annotation.*;


/**
 * 定义一个注解，通过这个注解获取bean
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Super {
}
