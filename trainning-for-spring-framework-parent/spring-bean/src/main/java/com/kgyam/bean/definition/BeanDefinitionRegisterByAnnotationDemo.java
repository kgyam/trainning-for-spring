package com.kgyam.bean.definition;

import com.kgyam.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 注册Spring Bean
 * <p>
 * 小细节：即便@Import和@Component同时将Config注册到容器中，
 * 容器里面最终只有一个bean对象。
 */


@Import(BeanDefinitionRegisterByAnnotationDemo.Config.class)
public class BeanDefinitionRegisterByAnnotationDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        registryByAnnotationComponent(beanFactory);
//        registryByAnnotationImport();
//        registryByConfigClass();
        beanFactory.close();
    }


    @Component
// 第二种方法:内部类加上组件注解，让容器扫描到内部类中的@Bean
    public static class Config {
        @Bean
        public User user() {
            User user = new User();
            user.setName("kgyam");
            user.setAge(18);
            return user;
        }
    }

    /**
     * 第三种方式：使用@Component方式
     * 这种方式需要使用到ComponentScan，否则无法把Config注册到容器
     * 因为内部类和当前类属于同级包，就算register当前类，
     * 容器也无法扫描到Config这个类
     */
    static void registryByAnnotationComponent(AnnotationConfigApplicationContext beanFactory) {
        /**
         * 设置容器扫描路径，不加上这个
         * @Component无法被注册到容器中
         */
        beanFactory.scan("com.kgyam.bean.definition");
        beanFactory.register(BeanDefinitionRegisterByAnnotationDemo.class);
        beanFactory.refresh();
        Map<String, User> usr = beanFactory.getBeansOfType(User.class);
        Map<String, Config> configMap = beanFactory.getBeansOfType(Config.class);
        System.out.println(usr);
        System.out.println(configMap);
    }

    /**
     * 第二种方法:@Import方式让容器扫描到Config中的@Bean
     */
    static void registryByAnnotationImport(AnnotationConfigApplicationContext beanFactory) {
//      将BeanDefinitionRegisterDemo类注册到容器里面
        beanFactory.register(BeanDefinitionRegisterByAnnotationDemo.class);
        beanFactory.refresh();
        Map<String, User> usr = beanFactory.getBeansOfType(User.class);
        Map<String, Config> configMap = beanFactory.getBeansOfType(Config.class);
        BeanDefinitionRegisterByAnnotationDemo demo = beanFactory.getBean(BeanDefinitionRegisterByAnnotationDemo.class);
        System.out.println(usr);
        System.out.println(configMap);
        System.out.println(demo);
    }


    /**
     * 第一种方法，register参数传入Config.class
     */
    static void registryByConfigClass(AnnotationConfigApplicationContext beanFactory) {
        /**
         * 这里的register参数是Config.class
         */
        beanFactory.register(Config.class);
        beanFactory.refresh();
        Map<String, User> usr = beanFactory.getBeansOfType(User.class);
        Map<String, Config> configMap = beanFactory.getBeansOfType(Config.class);
        /**
         * 没有BeanDefinitionRegisterDemo这个bean，会报错
         */
//        BeanDefinitionRegisterDemo demo = beanFactory.getBean(BeanDefinitionRegisterDemo.class);
        System.out.println(usr);
        System.out.println(configMap);
//        System.out.println(demo);
    }


}
