package com.kgyam.spring.annotation.attributeOverride;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author kg yam
 * @date 2021-04-30 10:00
 * @since
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@OverrideScan
public @interface OverrideScan2 {

    String[] basePackages() default {};

    @AliasFor("basePackages")
    String[] packages() default {};
}
