package com.kgyam.spring.annotation.attributeOverride;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author kg yam
 * @date 2021-04-30 9:59
 * @since
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@ComponentScan
public @interface OverrideScan {

    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
    String[] basePackages() default {"#"};
}
