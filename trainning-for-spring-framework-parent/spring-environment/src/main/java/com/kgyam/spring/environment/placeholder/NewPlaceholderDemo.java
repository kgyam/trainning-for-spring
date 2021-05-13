package com.kgyam.spring.environment.placeholder;

import com.kgyam.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Spring 3.1之后使用新的占位符处理器
 * {@link PropertySourcesPlaceholderConfigurer}
 */
public class NewPlaceholderDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/new-placeholders-resolver.xml");
        applicationContext.refresh();
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);
        applicationContext.close();
    }
}
