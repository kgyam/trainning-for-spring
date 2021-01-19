package com.kgyam.dependency.injection.custom_injection_demo;

import com.kgyam.dependency.injection.annotations.CustomAutoWired;
import com.kgyam.dependency.injection.annotations.CustomInject;
import com.kgyam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * {@link CustomAutoWired}方式:自定义注解上标记上{@link Autowired}
 * <p>
 * {@link CustomInject}方式:需要注册一个AutowiredAnnotationBeanPostProcessor
 *
 * @author kg yam
 * @date 2021-01-12 11:57
 * @since
 */
public class CustomAutoWiredInjectionDemo {

    @CustomAutoWired
    private User user;

    @Autowired
    private User user3;

    @CustomInject
    @Qualifier(value = "user2")
    private User user2;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (CustomAutoWiredInjectionDemo.class);
        applicationContext.refresh ();
        CustomAutoWiredInjectionDemo demo = applicationContext.getBean (CustomAutoWiredInjectionDemo.class);
        System.out.println (demo.user);
        System.out.println (demo.user2);
        System.out.println (demo.user3);
        applicationContext.close ();
    }


    @Bean
    @Primary
    public User user() {
        User user = new User ();
        user.setName ("customAutoWired");
        return user;
    }

    @Bean
    public User user2() {
        User user = new User ();
        user.setName ("customInject");
        return user;
    }


    /**
     * 注册一个AutowiredAnnotationBeanPostProcessor。
     * 这样容器能识别到自定义注解
     * <p>
     * 这里使用static修饰是因为让该Bean提前注册
     * 如果不使用static修饰，这个只是普通method,需要该Demo类实例化后才能注册
     *
     * @return
     */
    @Bean
    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor ();
        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType (CustomInject.class);
        return autowiredAnnotationBeanPostProcessor;
    }
}
