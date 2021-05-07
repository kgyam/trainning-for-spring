package com.kgyam.spring.annotation.conditionAnnotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author kg yam
 * @date 2021-05-07 14:12
 * @since
 */
@Import(MapperPathConfiguration.class)
public class ConditionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (ConditionDemo.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment ();
        environment.setDefaultProfiles ("dev");
        environment.addActiveProfile ("prod");
        applicationContext.refresh ();

        String mapperPath = applicationContext.getBean ("mapperPath", String.class);
        System.out.printf ("mapper path : %s \n", mapperPath);
        applicationContext.close ();
    }
}
