package com.kgyam.dependency.injection.aware_demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 通过Aware接口注入具体的Bean，例如BeanFactory，ApplicationContext等
 *
 * @author kg yam
 * @date 2021-01-08 15:55
 * @since
 */
public class DependencyAwareInterfaceInjectionDemo implements BeanNameAware, BeanFactoryAware, ApplicationContextAware {

    private BeanFactory beanFactory;

    private ApplicationContext context;

    private String beanName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (DependencyAwareInterfaceInjectionDemo.class);
        applicationContext.refresh ();
        DependencyAwareInterfaceInjectionDemo demo = applicationContext.getBean (DependencyAwareInterfaceInjectionDemo.class);
        System.out.println ("beanFactory compare:" + (demo.beanFactory == applicationContext.getBeanFactory ()));
        System.out.println ("applicationContext compare:" + (applicationContext == demo.context));
        System.out.println ("beanName:" + demo.beanName);
        applicationContext.close ();

    }

    /**
     * 获取当前 BeanFactory
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * 获取上下文对象
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    /**
     * 获取容器中 Bean 的名称
     *
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
