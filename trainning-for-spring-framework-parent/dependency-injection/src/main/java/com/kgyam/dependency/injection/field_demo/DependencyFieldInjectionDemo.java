package com.kgyam.dependency.injection.field_demo;

import com.kgyam.dependency.injection.domain.UserHolder;
import com.kgyam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 字段注入示例
 * <p>
 * 字段注入是通过将注解标记到对应的字段上让上下文将Bean注入
 *
 * 这种方式会比较便利
 *
 * @Autowired
 * @Resource
 * @Inject
 */

public class DependencyFieldInjectionDemo {

    @Resource
    private UserHolder userHolder;

    @Autowired
    private UserHolder userHolder2;

//    private UserHolder userHolder3;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencyFieldInjectionDemo.class);

        applicationContext.refresh();

        DependencyFieldInjectionDemo config = applicationContext.getBean(DependencyFieldInjectionDemo.class);
        System.out.println("@Autowired :" + config.userHolder2);
        System.out.println("@Resource :" + config.userHolder);
        System.out.println("userHolder is the same of userHolder2 :" + (config.userHolder == config.userHolder2));
        applicationContext.close();
    }


    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }


    @Bean
    public User user() {
        User user = new User();
        user.setName("dali");
        user.setAge(10);
        return user;
    }
}
