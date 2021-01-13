package com.kgyam.dependency.injection.constructor_demo;

import com.kgyam.dependency.injection.domain.UserHolder;
import com.kgyam.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 通过BeanDefinition的API方式实现构造器注入
 *
 * @author kg yam
 * @date 2021-01-07 17:44
 * @since
 */

public class DependencyConstructorInjectionByApiDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        /*
        将当前类注册为配置类
         */
        applicationContext.register (DependencyConstructorInjectionByApiDemo.class);

        /*
        注册UserHolder的BeanDefinition
         */
        applicationContext.registerBeanDefinition ("userHolder", createBeanDefinition ());
        /*
        启动应用上下文
         */
        applicationContext.refresh ();
        UserHolder userHolder = applicationContext.getBean ("userHolder", UserHolder.class);
        System.out.println (userHolder);
        applicationContext.close ();
    }

    /**
     * 构建userHolder的BeanDefinition
     * 添加构造器注入元信息
     *
     * @return
     */
    static BeanDefinition createBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition (UserHolder.class);
        beanDefinitionBuilder.addConstructorArgReference ("user");
        return beanDefinitionBuilder.getBeanDefinition ();
    }

    @Bean
    public User user() {
        User user = new User ();
        user.setName ("kidchan");
        user.setAge (20);
        return user;
    }
}

