package com.kgyam.dependency.injection.method;

import com.kgyam.dependency.injection.domain.UserHolder;
import com.kgyam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 方法注入
 *
 * @Autowired
 * @Resource
 * @Bean
 * @Inject jsr330
 */
public class DependencyMethodInjectionDemo {

//
//    @Autowired
//    public DependencyMethodInjectionDemo(UserHolder userHolder){
//        this.userHolder=userHolder;
//    }


    private UserHolder userHolder;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencyMethodInjectionDemo.class);
        applicationContext.refresh();
        DependencyMethodInjectionDemo demo=applicationContext.getBean(DependencyMethodInjectionDemo.class);
        System.out.println(demo.userHolder);
        applicationContext.close();

    }


    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }


    @Bean
    public User user(){
        User user=new User();
        user.setName("generalyam");
        user.setAge(30);
        return user;
    }
}
