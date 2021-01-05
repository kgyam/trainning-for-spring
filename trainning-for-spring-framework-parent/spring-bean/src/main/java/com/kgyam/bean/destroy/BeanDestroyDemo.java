package com.kgyam.bean.destroy;

import com.kgyam.domain.SubUser;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring Bean销毁示例
 * <p>
 * 1.@PreDestroy   (不是通过AnnotationConfigApplicationContext方式启动无法使用)
 * 2.自定义destroy-method 通过Xml或者@Bean方式配置
 * 3.实现DisposableBean接口,重写destroy方法
 * 4.通过BeanDefinition方式设定自定义销毁方法
 * <p>
 * 执行顺序：
 *
 * @PreDestroy -> DisposableBean#destroy() -> 自定义销毁方法
 */

public class BeanDestroyDemo {
    public static void main(String[] args) {
//        destroyByXmlConfig();

//        destroyByBeanDefinition();

        destroyByBeanAnnotation();
    }


    /**
     * 通过xml配置设定销毁方法示例
     */
    static void destroyByXmlConfig() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/bean-destroy-context.xml");
        SubUser subUser = applicationContext.getBean("destroy-by-xml", SubUser.class);
        System.out.println(subUser);
        System.out.println("准备关闭应用上下文");
        applicationContext.close();
        System.out.println("已经关闭应用上下文");
    }

    /**
     * 通过@Bean的destroy-method设定销毁方法的示例
     */
    static void destroyByBeanAnnotation() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Config.class);
        applicationContext.refresh();
        applicationContext.getBean("sub-user", SubUser.class);
        System.out.println("准备关闭应用上下文");
        applicationContext.close();
        System.out.println("已经关闭应用上下文");
    }


    /**
     * 通过BeanDefinition设定销毁方法的示例
     */
    static void destroyByBeanDefinition() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SubUser.class);
        builder.setDestroyMethodName("destroyByBeanDefinition");
        applicationContext.refresh();
        /*
        需要先调用refresh启动容器，否则会抛出异常
         */
        applicationContext.registerBeanDefinition("sub-user", builder.getBeanDefinition());
        applicationContext.getBean("sub-user", SubUser.class);
        System.out.println("准备关闭应用上下文");
        applicationContext.close();
        System.out.println("已经关闭应用上下文");
    }


    @Configuration
    public static class Config {
        @Bean(name = "sub-user", destroyMethod = "destroyByBeanAnnotation")
        public SubUser subUser() {
            return new SubUser();
        }
    }
}
