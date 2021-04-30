package com.kgyam.spring.annotation.alias;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author kg yam
 * @date 2021-04-29 17:16
 * @since
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@MyComponentScan
public @interface MyComponentScan2 {

    @AliasFor(annotation = MyComponentScan.class,attribute = "myBasePackages")
    String[] myBasePackages2() default {};
}
