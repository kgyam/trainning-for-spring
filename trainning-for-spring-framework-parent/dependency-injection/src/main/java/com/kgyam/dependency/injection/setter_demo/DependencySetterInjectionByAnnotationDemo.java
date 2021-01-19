package com.kgyam.dependency.injection.setter_demo;

import com.kgyam.dependency.injection.domain.UserHolder;
import com.kgyam.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author kg yam
 * @date 2021-01-07 15:46
 * <p>
 * 通过注解方式Setter注入
 */
public class DependencySetterInjectionByAnnotationDemo {

    private static final String XML_LOCATION = "classpath:META-INF/dependency-setter-injection-context-2.xml";


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (DependencySetterInjectionByAnnotationDemo.class);

        /*
        这里说明注解方式的ApplicationContext对象，同时也可以使用XmlBeanDefinitionReader读取xml配置。两者之间并不冲突
         */
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader (applicationContext);
        beanDefinitionReader.loadBeanDefinitions (XML_LOCATION);

        applicationContext.refresh ();
        UserHolder userHolder = applicationContext.getBean ("userHolder", UserHolder.class);
        /*
        UserHolder{user=SuperUser{superId='super_id_123'} User{name='kgyam', age=18}}
        这里返回的是superUser的bean,因为设定了primary。容器只会找到主类注入到userHolder。

        如果没有读取这个xml配置，注入的是dali的这个bean。
         */
        System.out.println (userHolder);
        applicationContext.close ();
    }


    @Bean
    public UserHolder userHolder(User user) {
        UserHolder userHolder = new UserHolder ();
        userHolder.setUser (user);
        return userHolder;
    }

    @Bean
    public User user() {
        User user = new User ();
        user.setName ("dali");
        user.setAge (2);
        return user;
    }
}
