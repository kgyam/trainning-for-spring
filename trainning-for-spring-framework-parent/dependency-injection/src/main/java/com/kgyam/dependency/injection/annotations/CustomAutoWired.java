package com.kgyam.dependency.injection.annotations;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

/**
 * 自定义注入注解
 * 需要使用{@link Autowired}标记到该注解上
 *
 * @author kg yam
 * @date 2021-01-15 17:36
 * @since
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
public @interface CustomAutoWired {
}
