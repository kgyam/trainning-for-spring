package com.kgyam.dependency.injection;

import com.kgyam.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入的demo
 * <p>
 * 依赖注入可以通过手动编码注入，同时也可以通过auto-wiring的方式注入
 * <p>
 * 注入和查找的区别：个人认为，查找的主动的方式，由coder通过编码方式获取bean。
 * 注入的被动的，通过容器自己去注入进去。
 * <p>
 * <p>
 * 依赖来源：
 * 1.外部构建bean
 * 2.内建bean
 * 3.内建依赖(并非bean)
 */
public class InjectionDemo {


    public static void main(String[] args) {
        //构建
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        //依赖注入业务bean
        UserRepository repository = dependencyInjectBean(beanFactory);

        //依赖注入内建对象
        dependencyInjectObject(beanFactory, repository);

        //延迟注入
        dependencyInjectLazy(beanFactory, repository);

        //获取内建bean
        dependencyInjectBuildInBean(beanFactory);

    }


    /**
     * 依赖查找到用户仓库的bean，
     * 这个用户仓库bean里面的属性都是用过依赖注入的方式注入进去
     *
     * @param beanFactory
     * @return
     */
    static UserRepository dependencyInjectBean(BeanFactory beanFactory) {
        UserRepository repository = beanFactory.getBean(UserRepository.class);
        System.out.println(repository);
        return repository;
    }


    /**
     * 内建bean对象，一般是非我们构建的业务相关的bean对象，例如环境对象
     */
    static void dependencyInjectBuildInBean(BeanFactory beanFactory) {
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);
    }

    /**
     * 依赖注入的BeanFactory是DefaultListableBeanFactory，跟我们初始化的beanFactory不是同一个对象
     * 是容器内建依赖，但是还不是容器里面的一个bean对象
     * <p>
     * <p>
     * 尝试用过beanFactory获取beanFactory的bean对象。
     * 会报错，容器内并没有BeanFactory类型的bean,所以这个是容器内建依赖，并非bean对象。
     * 所以注入的是一个内建依赖
     */
    static void dependencyInjectObject(BeanFactory beanFactory, UserRepository repository) {
        BeanFactory injectionBeanFactory = repository.getBeanFactory();
        System.out.println(injectionBeanFactory);
        // return false
        System.out.println(injectionBeanFactory == beanFactory);

        /**
         * 报错：
         * Exception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.beans.factory.BeanFactory' available
         */
//        BeanFactory lookupBeanFactory = beanFactory.getBean(BeanFactory.class);
//        System.out.println(lookupBeanFactory);
    }


    /**
     * ObjectFactory是延迟注入的方式
     * 这里ObjectFactory获取的ApplicationContext类型的bean就是我们自己构建的BeanFactory，返回true
     */
    static void dependencyInjectLazy(BeanFactory beanFactory, UserRepository repository) {
        ApplicationContext applicationContext = repository.getObjectFactory().getObject();
        System.out.println(applicationContext);
        //return true
        System.out.println(applicationContext == beanFactory);
    }


}
