package com.kgyam.dependency.lookup;

import com.kgyam.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * 类型安全查找示例
 * 类型安全：容器中没有需要查找的bean的情况下，不会抛出异常信息
 * <p>
 * 单一查找：
 * BeanFactory#getBean()   类型不安全
 * ObjectFactory#getObject() 类型不安全
 * ObjectProvider#getIfAvailable() 类型安全
 * <p>
 * 集合查找：
 * ListableBeanFactory#getBeansOfType() 类型安全
 * ObjectProvider#stream()   类型安全
 * <p>
 * 在Spring Boot 和 Spring Cloud 源码中大量使用到ObjectProvider进行依赖查找
 * 推荐通过ObjectProvider进行依赖查找
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //启动容器
        applicationContext.refresh();

        /*
        单一查找
         */
        getBeanByBeanFactory(applicationContext);
        getObjectByObjectFactory(applicationContext);
        getIfAvailableByObjectProvider(applicationContext);


        /*
        集合查找
         */
        getBeansOfTypeByListableBeanFactory(applicationContext.getBeanFactory());
        streamByObjectProvider(applicationContext);

        //关闭容器
        applicationContext.close();
    }


    /*
    ##############单一查找#########################
     */

    /**
     * 类型不安全
     * 容器中没有这个bean会抛出异常
     *
     * @param beanFactory
     */
    static void getBeanByBeanFactory(BeanFactory beanFactory) {
        lookup("getBeanByBeanFactory", () -> beanFactory.getBean(User.class));
    }

    /**
     * 类型不安全
     * 容器中没有这个bean会抛出异常
     *
     * @param beanFactory
     */
    static void getObjectByObjectFactory(BeanFactory beanFactory) {
        lookup("getObjectByObjectFactory", () -> {
            ObjectFactory<User> objectFactory = beanFactory.getBeanProvider(User.class);
            objectFactory.getObject();
        });
    }


    /**
     * 类型安全
     * 容器中没有这个bean返回null
     *
     * @param beanFactory
     */
    static void getIfAvailableByObjectProvider(BeanFactory beanFactory) {
        lookup("getIfAvailableByObjectProvider", () -> {
            ObjectProvider<User> objectProvider = beanFactory.getBeanProvider(User.class);
            User u = objectProvider.getIfAvailable();

        });
    }


    /*
    ##############集合查找#########################
     */

    /**
     * 类型安全
     * 没有找到相关类型的bean返回一个空的map集合
     *
     * @param listableBeanFactory
     */
    static void getBeansOfTypeByListableBeanFactory(ListableBeanFactory listableBeanFactory) {
        lookup("getBeansOfTypeByListableBeanFactory", () -> {
            Map<String, User> map = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("getBeansOfTypeByListableBeanFactory map:" + map);
        });
    }


    /**
     * 类型安全
     *
     * @param beanFactory
     */
    static void streamByObjectProvider(BeanFactory beanFactory) {
        lookup("streamByObjectProvider", () -> {
            beanFactory.getBeanProvider(User.class).stream().forEach(System.out::println);
        });

    }


    static void lookup(String msg, Runnable r) {
        System.err.println("execute source:" + msg);
        try {
            r.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
