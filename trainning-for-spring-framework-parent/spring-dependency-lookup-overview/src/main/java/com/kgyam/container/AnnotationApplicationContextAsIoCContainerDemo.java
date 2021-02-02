package com.kgyam.container;

import com.kgyam.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 使用注解扫描容器
 */
public class AnnotationApplicationContextAsIoCContainerDemo {


    public static void main(String[] args) {
        /**
         * 初始化注解类型容器
         */
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        /**
         * 将AnnotationApplicationContextAsIoCContainer注册为到容器中,作为配置文件
         */
        applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);
        /**
         * refresh()方法是启动容器
         */
        applicationContext.refresh();
        /**
         * 获取下面用@bean注解标记的user bean对象
         */
        User user = applicationContext.getBean(User.class);
        System.out.println(user);
        /**
         * 关闭容器
         */
        applicationContext.close();
    }


    @Bean
    public User user() {
        User user = new User();
        user.setAge(18);
        user.setName("kg-annotation");
        return user;
    }
}
