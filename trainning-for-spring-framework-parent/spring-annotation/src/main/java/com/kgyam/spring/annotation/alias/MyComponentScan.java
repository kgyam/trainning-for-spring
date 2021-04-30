package com.kgyam.spring.annotation.alias;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;


/**
 * @author kg yam
 * @date 2021-04-29 17:04
 * @since
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@ComponentScan
public @interface MyComponentScan {

    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
    String[] myBasePackages() default {};
}
