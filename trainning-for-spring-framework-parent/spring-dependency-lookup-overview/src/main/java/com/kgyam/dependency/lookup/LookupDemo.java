package com.kgyam.dependency.lookup;

import com.kgyam.annotation.Super;
import com.kgyam.domain.SuperUser;
import com.kgyam.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class LookupDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-lookup-context.xml");
//        lookupByIdOrName(beanFactory);
//        lookupByClass(beanFactory);
//        lookupCollection(beanFactory);
//        lookupByAnnotation(beanFactory);
        lookupLazy(beanFactory);
    }


    /**
     * 根据bean的id或者name进行依赖查找
     *
     * @param beanFactory
     */
    static void lookupByIdOrName(BeanFactory beanFactory) {
        String id = "user";
        String name = "kgyam";
        User user = (User) beanFactory.getBean(name);
        System.out.println(user.toString());
    }


    /**
     * 延迟查找bean,通过ObjectFactory方式查找bean。这种方式非平时开发的延迟加载
     *
     * ObjectFactory这个对象需要定义到bean配置文件中
     *
     *
     * @param beanFactory
     */
    static void lookupLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找：" + user);
    }

    /**
     * 根据class类型依赖查找bean
     * 此方法是spring3才支持的，java5出现泛型而新增的依赖查找方法
     * <p>
     * 这种方式的依赖查找，如果相同class类型对应了多个bean实例，会抛出异常,如下：
     * Exception in thread "main" org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.kgyam.domain.User' available: expected single matching bean but found 2: user,user2
     *
     * @param beanFactory
     */
    static void lookupByClass(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println(user.toString());
    }


    /**
     * 通过ListableBeanFactory的getBeansOfType 依赖查找bean集合
     *
     * @param beanFactory
     */
    static void lookupCollection(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> result = listableBeanFactory.getBeansOfType(User.class);
            result.forEach((k, v) -> {
                System.out.println(k);
                System.out.println(v.toString());
            });
        }
    }


    /**
     * 通过注解类型查找bean对象集合，key为bean的id，value为bean
     * 这种方式也是spring3之后才出现的，注解的java5的新特性
     * 这种通过注解类型的获取bean，需要使用ListableBeanFactory获取
     *
     * @param beanFactory
     */
    static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> result = listableBeanFactory.getBeansWithAnnotation(Super.class);
            result.forEach((k, v) -> {
                System.out.println("key: " + k);
                SuperUser superUser = (SuperUser) v;
                System.out.println(superUser.toString());
            });
        }
    }
}