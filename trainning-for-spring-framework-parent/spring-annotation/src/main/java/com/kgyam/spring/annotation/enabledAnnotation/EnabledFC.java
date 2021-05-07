package com.kgyam.spring.annotation.enabledAnnotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Enabled注解一般用于激活某个模块功能
 * Enabled注解中需要导入激活模块的相关配置类以此激活模块
 * 导入的方式有三种：
 * 1.通过@Import导入Configuration类实现
 * 2.通过@Import导入ImportSelector接口实现
 * 3.通过@Import导入ImportBeanDefinitionRegistrar实现
 *
 * @author kg yam
 * @date 2021-05-06 11:41
 * @since
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(FCConfiguration.class)
//@Import(FCImportSelector.class)
@Import(FcImportBeanDefinitionRegistrar.class)
public @interface EnabledFC {
}
