package com.kgyam.spring.environment.placeholder;

import com.kgyam.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
 * 旧的占位符处理方式,Spring 3.1之前
 */
public class OldPlaceholderDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/old-placeholders-resolver.xml");
        applicationContext.refresh();
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);
        applicationContext.close();
    }
}
