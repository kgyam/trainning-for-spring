package org.kgyam.spring.conversion.converter.demo;

import com.kgyam.domain.Users;
import org.kgyam.spring.conversion.converter.LocalProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author kg yam
 * @date 2021-04-07 15:38
 * @since
 */
public class ConditionalGenericConverterDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext ("classpath:META-INF/conditional-generic-converter.xml");
//        Users users = applicationContext.getBean (Users.class);
        LocalProperties localProperties=applicationContext.getBean (LocalProperties.class);
        System.out.println (localProperties);
    }
}
