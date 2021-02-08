package com.kgyam.destroy;

import com.kgyam.domain.DestroyDomain;
import com.kgyam.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 销毁Bean的方式
 * 1.主动调用BeanFactory的destroyBean方法
 * 2.调用ApplicationContext的close方法
 *
 * 第一种方式的逻辑：
 * 销毁方法核心在AbstractBeanFactory的destroy方法
 * 逻辑是创建一个对应的销毁适配器DisposableBeanAdapter 进行对应bean的销毁
 * 这里的销毁只是执行我们预先定义好的销毁方法，并不是gc：
 * 1.销毁前置操作
 * 2.@PreDestroy
 * 3.DisposableBean#destroy
 * 4.自定义销毁
 *
 *
 *
 * 第二种方式的逻辑：
 * 在Bean注册的时候会通过AbstractBeanFactory#registerDisposableBeanIfNecessary(doCreateBean的时候调用)
 * 判断该Bean是否有销毁方法的定义，如果有就创建一个对应的销毁适配器DisposableBeanAdapter
 * 然后put到disposableBeans这个Map中(key为beanName),在applicationContext调起
 * doClose方法时会遍历这个集合然后执行对应的销毁方法逻辑，除此之外还会清除掉容器里面的缓存
 * @author kg yam
 * @date 2021-02-08 14:21
 * @since
 */
public class DestroyDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (DestroyDemo.class);
        applicationContext.refresh ();
        User user = applicationContext.getBean (User.class);
        applicationContext.getDefaultListableBeanFactory ().destroyBean ("user", user);
        applicationContext.close ();
    }


    @Bean
    public User user() {
        return User.createUser ();
    }

    @Bean
    public DestroyDomain destroyDomain() {
        return new DestroyDomain ();
    }
}
