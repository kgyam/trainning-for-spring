package com.kgyam.spring.annotation.enabledAnnotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

/**
 * Enabled注解激活模块demo，
 *
 * 个人理解Enabled是为简化模块复杂的初始化。
 * 模块开发者定义一套默认的启动配置，让复杂的代码隐藏在配置类内部。
 * 用户无需关注复杂的初始化过程，只需要利用一个注解即可启动默认配置的功能
 *
 * @author kg yam
 * @date 2021-05-06 12:01
 * @since
 */
@EnabledFC
public class EnabledDemo {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (EnabledDemo.class);
        applicationContext.refresh ();

        FCClient fcClient = applicationContext.getBean (FCClient.class);
        Assert.notNull (fcClient, "fcClient must not be null");
        System.out.println (fcClient.toString ());
        applicationContext.close ();
    }
}
