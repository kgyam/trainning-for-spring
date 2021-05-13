package com.kgyam.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 通过@Value获取外部化配置属性
 */
public class ValueAnnotationDemo {

    @Value("${user.name}")
    private String name;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ValueAnnotationDemo.class);
        applicationContext.refresh();
        ValueAnnotationDemo demo = applicationContext.getBean(ValueAnnotationDemo.class);
        System.out.println("user.name=" + demo.name);
        applicationContext.close();
    }
}
