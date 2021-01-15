package com.kgyam.dependency.injection.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;

import java.lang.annotation.*;

/**
 * 自定义注入注解
 * 无需标记{@link Autowired}
 * 但是需要设定到{@link AutowiredAnnotationBeanPostProcessor}
 *
 * @author kg yam
 * @date 2021-01-15 17:43
 * @since
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomInject {
}
