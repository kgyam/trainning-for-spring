package com.kgyam.dependency.injection.injection_type_demo;

import com.kgyam.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 演示各种类型的注入
 * 基本类型
 * 数组
 * 集合类型
 * 枚举
 *
 * @author kg yam
 * @date 2021-01-12 11:57
 * @since
 */
public class InjectionTypeDemo {


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext ("classpath:META-INF/dependency-injection-type-context.xml");
        Map<String, User> users = applicationContext.getBeansOfType (User.class);
        users.forEach ((k, v) -> {
            System.out.println (v.toString ());
        });
    }
}
