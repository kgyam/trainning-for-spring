package com.kgyam.dependency.injection.constructor;

import com.kgyam.dependency.injection.domain.UserHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过xml配置实现构造器注入
 *
 * @author kg yam
 * @date 2021-01-07 17:34
 * @since
 */
public class DependencyConstructorInjectionByXmlDemo {

    private static final String XML_LOCATION = "classpath:META-INF/dependency-constructor-injection-context.xml";

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext (XML_LOCATION);
        UserHolder userHolder = applicationContext.getBean ("userHolder", UserHolder.class);
        System.out.println (userHolder);
    }
}
