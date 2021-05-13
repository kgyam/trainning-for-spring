package com.kgyam.spring.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入Environment对象的方式
 * 1.通过 {@link EnvironmentAware}
 * 2.通过{@link Autowired}
 */
public class InjectEnvironmentDemo implements EnvironmentAware {

    private Environment environment;

    @Autowired
    private Environment environment2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectEnvironmentDemo.class);
        applicationContext.refresh();

        InjectEnvironmentDemo demo = applicationContext.getBean(InjectEnvironmentDemo.class);
        System.out.println(demo.environment == demo.environment2);
        System.out.println(demo.environment);
        applicationContext.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
