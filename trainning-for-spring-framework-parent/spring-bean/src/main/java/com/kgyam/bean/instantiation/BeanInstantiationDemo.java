package com.kgyam.bean.instantiation;

import com.kgyam.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 官方文档: https://docs.spring.io/spring-framework/docs/5.2.12.RELEASE/spring-framework-reference/core.html#beans-factory-class
 * <p>
 * Spring Bean 常规实例化的示例
 * <p>
 * 1.构造方法
 * 2.静态工厂方法
 * 3.工厂方法
 * 4.FactoryBean
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-instantiation-context.xml");
        User constructorUser = instantiationByConstructor(beanFactory);
        User staticUser = instantiationByStaticMethod(beanFactory);
        User factoryMethodUser = instantiationByFactoryMethod(beanFactory);
        User factoryBeanUser = instantiationByFactroyBean(beanFactory);
        System.out.println(staticUser == factoryMethodUser);

    }


    static User instantiationByConstructor(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user-by-constructor", User.class);
        System.out.println("instantiationByConstructor user:" + user);
        return user;
    }

    /**
     * 通过静态方法实例化bean
     * 实际中比较少用
     *
     * @param beanFactory
     */
    static User instantiationByStaticMethod(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user-by-static-method", User.class);
        System.out.println("instantiationByStaticMethod user:" + user);

        return user;
    }


    /**
     * 通过工厂方法实例化bean
     * 实际中比较少用
     *
     * @param beanFactory
     * @return
     */
    static User instantiationByFactoryMethod(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user-by-factory-method", User.class);
        System.out.println("instantiationByFactoryMethod user:" + user);
        return user;
    }


    /**
     * 通过FactoryBean实例化
     *
     * @param beanFactory
     * @return
     */
    static User instantiationByFactroyBean(BeanFactory beanFactory) {

        User user = beanFactory.getBean("user-factory-bean", User.class);
        System.out.println("instantiationByFactroyBean user:" + user);
        return user;
    }
}
