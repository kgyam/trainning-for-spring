package com.kgyam.bean.definition;

import com.kgyam.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.stream.Stream;

/**
 * 用Spring Api的方式注册bean definition
 */
public class BeanDefinitionRegistryByApiDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanDefinitionRegistryByApiDemo.class);
        applicationContext.refresh();
        beanDefinitionRegistryByBeanName(applicationContext, "general-yam", User.class);
        beanDefinitionRegistryNonName(applicationContext, User.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames).forEach(System.out::println);
        Map<String, User> beans = applicationContext.getBeansOfType(User.class);
        System.out.println(beans);
        applicationContext.close();
    }


    static void beanDefinitionRegistryByBeanName(BeanDefinitionRegistry registry, String beanName, Class<?> type) {

        registry(registry, beanName, type);
    }


    static void beanDefinitionRegistryNonName(BeanDefinitionRegistry registry, Class<?> type) {
        registry(registry, null, type);
    }

    static void registry(BeanDefinitionRegistry registry, String beanName, Class<?> type) {

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(type);
        builder.addPropertyValue("name", "kgyam").addPropertyValue("age", 17);
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        } else {
            /*
            非命名方式注册bean definition,bean name 是类全限定路径名#数字
             */
            BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
        }
    }
}
