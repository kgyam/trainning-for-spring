package com.kgyam.dependency.injection.annotations;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * 自定义分组
 *
 * @author kg yam
 * @date 2021-01-08 16:35
 * @since
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier
public @interface CustomGroup {
}
