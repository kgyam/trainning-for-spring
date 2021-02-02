package com.kgyam.instantiation;

import com.kgyam.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * bean实例化前置操作&源码示例
 */
public class BeforeInstantiationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.getDefaultListableBeanFactory().addBeanPostProcessor(new CustomInstantiationAwareBeanPostProcessor());
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("classpath:META-INF/instantiation-bean-context.xml");
        applicationContext.refresh();
        User user = applicationContext.getBean(User.class);
        System.out.println(user);
        applicationContext.close();
    }
}
