package com.kgyam.spring.environment;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖查找方式获取Environment对象
 * 1. 通过applicationContext.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);
 * 2. 通过获取applicationContext，从而获取Environment对象
 */
public class LookupEnvironmentDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectEnvironmentDemo.class);
        applicationContext.refresh();

        Environment environment = applicationContext.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);
        Environment environment1 = applicationContext.getEnvironment();
        System.out.println(environment1==environment);
        applicationContext.close();
    }
}
