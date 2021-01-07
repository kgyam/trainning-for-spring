package com.kgyam.dependency.injection.setter;

import com.kgyam.dependency.injection.domain.UserHolder;
import com.kgyam.domain.SuperUser;
import com.kgyam.domain.User;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 没有调试成功，后续再解决
 * <p>
 * ’@Bean’的AutoWired同样可以配置自动绑定，
 * 但是在Spring5.1之后已经被废弃
 *
 * @author kg yam
 * @date 2021-01-07 16:59
 * @since
 */
public class DependencySetterInjectionByAutowiringAnnotationDemo {
    private static final String XML_LOCATION = "classpath:META-INF/dependency-setter-injection-context-2.xml";


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (DependencySetterInjectionByAutowiringAnnotationDemo.class);
        BeanDefinitionReader reader = new XmlBeanDefinitionReader (applicationContext);
        reader.loadBeanDefinitions (XML_LOCATION);

        applicationContext.refresh ();

//        UserHolder userHolder = applicationContext.getBean ("userHolder", UserHolder.class);
//        System.out.println (userHolder);
        UserHolder userHolder2 = applicationContext.getBean ("userHolder2", UserHolder.class);
        System.out.println (userHolder2);
        applicationContext.close ();

    }

//    @Bean(autowire = Autowire.BY_TYPE)
//    public UserHolder userHolder(SuperUser superUser) {
//        UserHolder userHolder = new UserHolder ();
//        userHolder.setUser (superUser);
//        return userHolder;
//    }

    @Bean(autowire = Autowire.BY_NAME)
    public UserHolder userHolder2(User user) {
        UserHolder userHolder = new UserHolder ();
        userHolder.setUser (user);
        return userHolder;
    }

}
