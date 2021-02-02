package com.kgyam.bean.initialization;

import com.kgyam.domain.SubUser;
import com.kgyam.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
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
 * 1.通过注解@PostConstruct  (不是通过AnnotationConfigApplicationContext方式启动无法使用)
 * 2.通过xml配置init-method方法 或者 通过@Bean注解的init-method方法
 * 3.实现InitializingBean
 * 4.BeanDefinitionBuilder#setInitMethodName()
 * <p>
 * 执行顺序：
 *
 * @PostConstruct -> InitializingBean#afterPropertiesSet() -> 自定义init-method
 * <p>
 * <p>
 * 延迟初始化方式：
 * 1.@Lazy方法
 * 2.xml配置
 * 3.通过BeanDefinitionBuilder#setLazyInit()
 */

public class BeanInitializationDemo {
    public static void main(String[] args) {
        initByAnnotation ();
//        lazyInitUser();
//        initByBeanDefinition();
//        lazyInitUserByXmlConfig();
    }


    /**
     * bean初始化：
     * 通过@Bean注解的init-method方法
     */
    static void initByAnnotation() {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        //注册注解标记的组件class
        applicationContext.register (Config.class);
        //启动spring应用上下文
        applicationContext.refresh ();
        SubUser subUser = applicationContext.getBean ("init-by-bean-annotation", SubUser.class);
        System.out.println (subUser);
        applicationContext.close ();
    }

    /**
     * bean初始化：
     * 通过xml配置的init-method
     */
    static void initByInitMethod() {
        /*
        启动读取xml配置的应用上下文
         */
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext ("classpath:META-INF/bean-initialization-context.xml");
        System.out.println ("上下文启动完成");
        SubUser subUser = applicationContext.getBean ("init-by-init-method", SubUser.class);
        System.out.println (subUser);
    }

    /**
     * bean初始化&延迟加载：
     * 通过BeanDefinitionBuilder#setInitMethodName()
     * 通过BeanDefinitionBuilder#
     */
    static void initByBeanDefinition() {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        //启动应用上下文
        applicationContext.refresh ();
        /*
        构建BeanDefinitionBuilder
         */
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition (SubUser.class);
        /*
        设置初始化方法
         */
        builder.setInitMethodName ("initByBeanDefinition");
        /*
        设置是否延迟加载
         */
        //builder.setLazyInit(true);
        BeanDefinition beanDefinition = builder.getBeanDefinition ();

        /*
        向容器注册BeanDefinition,
         */
        // TODO: 2021/1/5  question:这种方式能在容器启动后进行注册？
        String beanName = "init-by-bean-definition";
        applicationContext.registerBeanDefinition (beanName, beanDefinition);

        SubUser subUser = applicationContext.getBean (beanName, SubUser.class);
        System.out.println (subUser);
        /*
        关闭应用上下文
         */
        applicationContext.close ();

    }


    /**
     * 延迟加载：
     * 通过Xml配置的lazy-init
     */
    static void lazyInitUserByXmlConfig() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext ("classpath:META-INF/bean-initialization-context.xml");
        System.out.println ("上下文启动完成");
        SubUser subUser = beanFactory.getBean ("init-lazy-sub-user", SubUser.class);
        System.out.println (subUser);

    }

    /**
     * 延迟加载：
     * 通过@Bean的lazy-init
     */
    static void lazyInitUser() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();

        //注册Config bean definition 需要在启动容器前执行
        applicationContext.register (Config.class);
        System.out.println ("准备启动上下文");
        //启动spring应用上下文
        applicationContext.refresh ();
        System.out.println ("启动上下文完成");
        SubUser subUser = applicationContext.getBean ("lazy-init-subuser", SubUser.class);
        System.out.println (subUser);
        applicationContext.close ();
    }

    @Configuration
    static class Config {

        @Bean(name = "init-by-bean-annotation", initMethod = "initByAnnotation")
        public SubUser subUser() {
            return new SubUser ();
        }

        @Bean(name = "lazy-init-subuser", initMethod = "lazyInit")
        @Lazy
        public SubUser user() {
            return new SubUser ();
        }
    }
}
