package com.kgyam.dependency.injection.constructor_demo;

import com.kgyam.dependency.injection.domain.UserHolder;
import com.kgyam.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 通过注解方式实现构造器注入
 *
 * @author kg yam
 * @date 2021-01-07 17:40
 * @since
 */
public class DependencyConstructorInjectionByAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (DependencyConstructorInjectionByAnnotationDemo.class);
        applicationContext.refresh ();
        UserHolder userHolder = applicationContext.getBean ("userHolder", UserHolder.class);
        System.out.println (userHolder);
        applicationContext.close ();
    }


    /**
     * 构造器注入:主动调用对象的构造器方法
     *
     * @param user
     * @return
     */
    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder (user);
    }

    @Bean
    public User user() {
        User user = new User ();
        user.setName ("dali");
        user.setAge (2);
        return user;
    }
}
