package org.kgyam.spring.conversion;

import com.kgyam.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomizedPropertyEditorDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:META-INF/conversion-context.xml");
        User user=applicationContext.getBean("user", User.class);
        System.out.println(user);
    }
}
