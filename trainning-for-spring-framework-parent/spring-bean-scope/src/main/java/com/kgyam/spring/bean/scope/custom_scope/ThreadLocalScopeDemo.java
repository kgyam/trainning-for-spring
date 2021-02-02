package com.kgyam.spring.bean.scope.custom_scope;

import com.kgyam.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * 自定义bean作用域,
 * 1.实现Scope接口，并根据需求实现对应的接口方法
 * 2.定义Scope的name,在指定Scope的时候用到
 * 3.容器需要通过addBeanFactoryPostProcessor的方式添加一个处理器，
 * 并调用ConfigurableListableBeanFactory#registerScope(scopeName,scopeInstance)方法
 *
 * @author kg yam
 * @date 2021-01-29 11:46
 * @see ThreadLocalScope
 * @since
 */
public class ThreadLocalScopeDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();

        applicationContext.register (ThreadLocalScopeDemo.class);
        applicationContext.addBeanFactoryPostProcessor (beanFactory -> {
            beanFactory.registerScope (ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope ());
        });
        applicationContext.refresh ();

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Thread t = new Thread (() -> {
                for (int j = 0; j < 5; j++) {
                    User u = applicationContext.getBean (User.class);
                    System.out.println ("thread-" + (finalI) + " user:" + u.getName ());
                }
            });
            t.start ();
            t.join ();
        }

        applicationContext.close ();
    }


    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public User user() {
        User usr = new User ();
        usr.setName (String.valueOf (Thread.currentThread ().getId ()));
        return usr;
    }
}
