package com.kgyam.bean.instantiation;


import com.kgyam.domain.User;
import com.kgyam.domain.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Spring Bean非常规方式
 * <p>
 * 1.spi方式
 * 2.
 */
public class BeanInstantiationSpecialDemo {


    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-instantiation-special-context.xml");
        instantiationByServiceLoader(beanFactory);
        instantiationByServiceList(beanFactory);
    }


    /**
     * 通过ServiceLoaderFactoryBean方式实例化bean
     *
     * @param beanFactory
     */
    static void instantiationByServiceLoader(BeanFactory beanFactory) {
        ServiceLoader<UserService> services = beanFactory.getBean("service-loader-factory-bean", ServiceLoader.class);
        Iterator<UserService> iterator = services.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next().createUser();
            System.out.println(user);
        }
    }


    static void instantiationByServiceList(BeanFactory beanFactory) {
        ServiceLoader<UserService> services = beanFactory.getBean("service-loader-factory-bean", ServiceLoader.class);

        Iterator<UserService> iterator = services.iterator();
        while (iterator.hasNext()) {
            UserService userService = iterator.next();
            User user = userService.createUser();
            System.out.println(user);
        }
    }

}
