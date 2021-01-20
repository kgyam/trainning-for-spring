package com.kgyam.dependency.source;

import com.kgyam.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Singleton Dependency示例
 * <p>
 * 通过SingletonBeanRegistry#registerSingleton()方式注册一个单例对象到容器中
 *
 * 特点:
 * 1.可以是非POJO对象
 * 2.无生命周期管理(无法实现以上callback InitializingBean#afterPropertiesSet,DisposableBean#destroy)
 * 3.无法延迟化加载
 * 4.可以依赖注入、依赖查找
 *
 * @author kg yam
 * @date 2021-01-20 15:56
 * @since
 */
public class SingletonDependencySourceDemo {


    @Autowired
    private User user;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (SingletonDependencySourceDemo.class);

        //构建单例
        User user = new User ();
        user.setName ("singleton");
        user.setAge (20);

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory ();
        //SingletonBeanRegistry#registerSingleton
        beanFactory.registerSingleton ("singleton_user", user);
        applicationContext.refresh ();
        dependencyInjection (beanFactory);
        dependencyLookup (beanFactory);
        applicationContext.close ();
    }


    private static void dependencyInjection(BeanFactory beanFactory) {
        SingletonDependencySourceDemo demo = beanFactory.getBean (SingletonDependencySourceDemo.class);
        System.out.println ("dependencyInjection User:" + demo.user);
    }

    private static void dependencyLookup(BeanFactory beanFactory) {
        User user = beanFactory.getBean ("singleton_user", User.class);
        System.out.println ("dependencyLookup User:" + user);
    }


}
