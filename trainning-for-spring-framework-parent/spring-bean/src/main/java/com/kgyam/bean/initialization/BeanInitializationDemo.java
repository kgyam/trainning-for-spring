package com.kgyam.bean.initialization;

import com.kgyam.domain.SubUser;
import com.kgyam.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;

/**
 * Spring Bean初始化示例
 * <p>
 * 1.通过注解@PostConstruct
 * 2.通过xml配置init-method方法 或者 通过@Bean注解的init-method方法
 * 3.实现InitializingBean
 * <p>
 * 执行顺序：
 *
 * @PostConstruct -> InitializingBean -> 自定义init-method
 * <p>
 * <p>
 * 延迟初始化方式：
 * 1.@Lazy方法
 * 2.xml配置
 */

public class BeanInitializationDemo {
    public static void main(String[] args) {
//        initByAnnotation();
//        lazyInitUser();
        lazyInitUserByXmlConfig();
    }


    static void initByAnnotation() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Config bean definition 需要在启动容器前执行
        applicationContext.register(Config.class);

        //启动spring应用上下文
        applicationContext.refresh();
        SubUser subUser = applicationContext.getBean("init-by-bean-annotation", SubUser.class);
        System.out.println(subUser);
        applicationContext.close();
    }

    static void initByInitMethod() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-initialization-context.xml");
        System.out.println("上下文启动完成");
        SubUser subUser = beanFactory.getBean("init-by-init-method", SubUser.class);
        System.out.println(subUser);
    }


    static void lazyInitUserByXmlConfig() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-initialization-context.xml");
        System.out.println("上下文启动完成");
        SubUser subUser=beanFactory.getBean("init-lazy-sub-user", SubUser.class);
        System.out.println(subUser);

    }

    static void lazyInitUser() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册Config bean definition 需要在启动容器前执行
        applicationContext.register(Config.class);
        System.out.println("准备启动上下文");
        //启动spring应用上下文
        applicationContext.refresh();
        System.out.println("启动上下文完成");
        SubUser subUser = applicationContext.getBean("lazy-init-subuser", SubUser.class);
        System.out.println(subUser);
        applicationContext.close();
    }

    @Configuration
    static class Config {

        @Bean(initMethod = "initByAnnotation", name = "init-by-bean-annotation")
        public SubUser subUser() {
            return new SubUser();
        }


        @Bean(name = "lazy-init-subuser", initMethod = "lazyInit")
        @Lazy
        public SubUser user() {
            return new SubUser();
        }
    }
}
