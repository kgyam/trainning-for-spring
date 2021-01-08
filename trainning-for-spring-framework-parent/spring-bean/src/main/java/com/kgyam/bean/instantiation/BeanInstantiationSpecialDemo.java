package com.kgyam.bean.instantiation;


import com.kgyam.domain.User;
import com.kgyam.domain.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * Spring Bean非常规方式
 * <p>
 * 1.SPI方式(XML、JAVA 注解、JAVA API)
 * 2.AutowireCapableBeanFactory#createBean(java.lang.Class,int,boolean)方式
 * 3.BeanDefinitionRegistry#registryBeanDefinition(String,BeanDefinition)
 */
public class BeanInstantiationSpecialDemo {


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext ("classpath:META-INF/bean-instantiation-special-context.xml");

//        instantiationByServiceLoader (applicationContext);
//        instantiationByServiceList(applicationContext);

//        instantiationByAutowireCapableBeanFactory (applicationContext);

        instantiationByBeanDefinitionRegistry ();
    }


    /**
     * 通过AutowireCapableBeanFactory实例化bean
     * AutowireCapableBeanFactory需要从applicationContext获取
     *
     * @param applicationContext
     */
    static void instantiationByAutowireCapableBeanFactory(ApplicationContext applicationContext) {
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory ();
        ServiceLoader<UserService> services = beanFactory.getBean ("service-loader-factory-bean", ServiceLoader.class);
        System.out.println (services);
    }

    /**
     * 通过ServiceLoaderFactoryBean方式实例化bean
     * <p>
     * 使用SPI的方式还是需要遵循SPI原来的规范，META-INF下设定好services的文件
     * 否则会无法找到这些实现类。
     *
     * @param beanFactory
     */
    static void instantiationByServiceLoader(BeanFactory beanFactory) {
        ServiceLoader<UserService> services = beanFactory.getBean ("service-loader-factory-bean", ServiceLoader.class);
        Iterator<UserService> iterator = services.iterator ();
        while (iterator.hasNext ()) {
            User user = iterator.next ().createUser ();
            System.out.println (user);
        }
    }


    /**
     * 这种方式能找到SPI接口所有实现类
     * <p>
     * 通过代码看到跟上面的那种方式是一样的，具体有什么区别，后期希望能找到答案
     * todo 找到ServiceListFactroyBean和ServiceLoaderFactoryBean区别
     *
     * @param beanFactory
     */
    static void instantiationByServiceList(BeanFactory beanFactory) {
        ServiceLoader<UserService> services = beanFactory.getBean ("service-loader-factory-bean", ServiceLoader.class);

        Iterator<UserService> iterator = services.iterator ();
        while (iterator.hasNext ()) {
            UserService userService = iterator.next ();
            User user = userService.createUser ();
            System.out.println (user);
        }
    }

    static void instantiationByBeanDefinitionRegistry() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (BeanInstantiationSpecialDemo.class);

        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition (User.class);
        beanDefinitionBuilder.addPropertyValue ("name", "dali");
        /*
        因为AnnotationConfigApplicationContext实现了BeanDefinitionRegistry接口,所以可以直接调用
         */
        applicationContext.registerBeanDefinition ("dali-user", beanDefinitionBuilder.getBeanDefinition ());
        applicationContext.refresh ();

        User user = applicationContext.getBean ("dali-user", User.class);
        System.out.println ("BeanDefinitionRegistry#registerBeanDefinition(String,Object):" + user);
        applicationContext.close ();
    }

}
