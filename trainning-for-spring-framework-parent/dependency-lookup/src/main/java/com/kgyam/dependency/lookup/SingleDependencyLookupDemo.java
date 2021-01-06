package com.kgyam.dependency.lookup;

import com.kgyam.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 单一依赖查找&延迟
 */
public class SingleDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SingleDependencyLookupDemo.class);
        applicationContext.refresh();
        lookupByBeanName(applicationContext);
        System.out.println("##############################################");
        lookupByType(applicationContext);
        System.out.println("##############################################");
        lookupByBeanNameAndType(applicationContext);
        System.out.println("##############################################");
        lookupLazyByObjectProvoider(applicationContext);
        System.out.println("##############################################");
        //lookupLazyByObjectFactory(applicationContext);
        applicationContext.close();

    }

    /**
     * 根据beanName依赖查找
     *
     * @param beanFactory
     */
    static void lookupByBeanName(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("lookupByBeanName user:" + user);
    }


    /**
     * 根据类型依赖查找
     *
     * @param beanFactory
     */
    static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("lookupByType user:" + user);
    }


    /**
     * 根据BeanName和类型依赖查找
     *
     * @param beanFactory
     */
    static void lookupByBeanNameAndType(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user", User.class);
        System.out.println("lookupByBeanNameAndType user:" + user);
    }

    /**
     * 延迟依赖查找
     * ObjectProvider继承了ObjectFactory，
     * ObjectProvider#getObject()是继承ObjectFactory#getObject()
     *
     * @param beanFactory
     */
    static void lookupLazyByObjectProvoider(BeanFactory beanFactory) {
        ObjectProvider<User> provider = beanFactory.getBeanProvider(User.class);
        User user = provider.getObject();
        System.out.println("lookupLazyByObjectProvoider user:" + user);
    }


    static void lookupLazyByObjectFactory(BeanFactory beanFactory) {
        //ObjectProvider本身是ObjectFactory的子接口
        ObjectFactory<User> objectFactory = beanFactory.getBeanProvider(User.class);
        User user = objectFactory.getObject();
        System.out.println("lookupLazyByObjectFactory user:" + user);
    }


    @Bean
    public User user() {
        User user = new User();
        user.setName("kgyam");
        user.setAge(18);
        return user;
    }

}