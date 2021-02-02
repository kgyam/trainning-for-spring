package com.kgyam.dependency.injection.autowired_annotation_demo;

import com.kgyam.dependency.injection.domain.UserHolder;
import com.kgyam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 可以将@Autowiredz注解标记到方法或者构造器上
 * 但这种并非真正的方法注入方式
 * <p>
 * 底层代码最终会通过反射方式将bean注入
 *
 * @Autowired
 * @Resource
 * @Bean 这个其实也是方法注入的一种方式，像UserHolder中的User就是通过这种方式注入到UserHolder中
 * @Inject JSR330
 */
public class MethodInjectionDemo {


    @Autowired
    public void init(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Resource
    public void init2(UserHolder userHolder2) {
        this.userHolder2 = userHolder2;
    }

    private UserHolder userHolder;

    private UserHolder userHolder2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (MethodInjectionDemo.class);
        applicationContext.refresh ();
        MethodInjectionDemo demo = applicationContext.getBean (MethodInjectionDemo.class);
        System.out.println (demo.userHolder);
        System.out.println (demo.userHolder2);
        applicationContext.close ();

    }


    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder (user);
    }


    @Bean
    public User user() {
        User user = new User ();
        user.setName ("generalyam");
        user.setAge (30);
        return user;
    }
}
