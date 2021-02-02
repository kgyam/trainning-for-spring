package com.kgyam.beandefinition;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解beanDefinition注册器示例
 * 常用的BeanDefinitionReader是面向资源的。AnnotatedBeanDefinitionReader方式可以面向类和注解
 *
 * AnnotatedBeanDefinitionReader是一个完全独立的bd reader，独立于其他面向资源的bd reader，并非BeanDefinitionReader的扩展类
 */
public class AnnotationBeanDefinitionReaderDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader reader=new AnnotatedBeanDefinitionReader(beanFactory);
        int registryBefore=beanFactory.getBeanDefinitionCount();
        reader.register(AnnotationBeanDefinitionReaderDemo.class);
        int registryAfter=beanFactory.getBeanDefinitionCount();
        System.out.println("bean definition added:"+(registryAfter-registryBefore));
        AnnotationBeanDefinitionReaderDemo demo=beanFactory.getBean(AnnotationBeanDefinitionReaderDemo.class);
        System.out.println(demo);
    }

}
