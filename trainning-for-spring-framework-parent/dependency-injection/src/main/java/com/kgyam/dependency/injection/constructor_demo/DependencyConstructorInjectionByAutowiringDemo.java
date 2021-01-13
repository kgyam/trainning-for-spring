package com.kgyam.dependency.injection.constructor_demo;

import com.kgyam.dependency.injection.domain.UserHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过自动绑定的方式实现构造器注入
 *
 * @author kg yam
 * @date 2021-01-07 18:05
 * @since
 */
public class DependencyConstructorInjectionByAutowiringDemo {
    private static final String XML_LOCATION = "classpath:META-INF/autowiring-dependency-constructor-injection-context.xml";

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext (XML_LOCATION);
        UserHolder userHolder = applicationContext.getBean ("userHolderByConstructor", UserHolder.class);
        System.out.println (userHolder);
    }

}
