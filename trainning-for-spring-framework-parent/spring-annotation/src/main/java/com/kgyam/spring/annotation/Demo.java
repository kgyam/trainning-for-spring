package com.kgyam.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author kg yam
 * @date 2021-04-29 14:06
 * @since
 */
@ComponentScan(basePackages = "com.kgyam.spring.annotation")
public class Demo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (Demo.class);
        applicationContext.refresh ();
        applicationContext.close ();
    }
}
