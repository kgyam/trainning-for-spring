package com.kgyam.dependency.injection.method_demo;

import com.kgyam.dependency.injection.domain.UserHolder;
import com.kgyam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 方法注入的方式有
 *
 * @Autowired
 * @Resource
 * @Bean 这个其实也是方法注入的一种方式，像UserHolder中的User就是通过这种方式注入到UserHolder中
 * @Inject JSR330
 */
public class DependencyMethodInjectionDemo {





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
        applicationContext.register (DependencyMethodInjectionDemo.class);
        applicationContext.refresh ();
        DependencyMethodInjectionDemo demo = applicationContext.getBean (DependencyMethodInjectionDemo.class);
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
