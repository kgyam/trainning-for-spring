package com.kgyam.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 外部化配置来源示例
 *
 * 使用@PropertySource注入配置文件的信息，这个注解需要跟@Configuration配合使用
 * 属性值通过@Value获取
 *
 * @author kg yam
 * @date 2021-01-20 16:58
 * @since
 *
 * @see PropertySource
 * @see Configuration
 * @see Value
 */


@PropertySource(value = "classpath:META-INF/external-config.properties",encoding = "UTF-8")
@Configuration
public class ExternalConfigurationSourceDemo {

    /**
     占位符里加冒号后加值 可以加上默认值兜底。防止配置文件读取失败抛出异常
     */
    @Value("${usr.id:-1}")
    private int id;
    @Value("${usr.name:null}")
    private String name;

    @Value("${usr.resource:classpath:META-INF/external-config.properties}")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();

        applicationContext.register (ExternalConfigurationSourceDemo.class);
        applicationContext.refresh ();
        ExternalConfigurationSourceDemo demo = applicationContext.getBean (ExternalConfigurationSourceDemo.class);
        System.out.println ("usr.id="+demo.id);
        System.out.println ("usr.name="+demo.name);
        System.out.println ("usr.resource="+demo.resource);
        applicationContext.close ();
    }
}
