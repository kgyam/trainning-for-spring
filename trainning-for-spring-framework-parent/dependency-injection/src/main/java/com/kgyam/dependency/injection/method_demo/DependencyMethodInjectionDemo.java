package com.kgyam.dependency.injection.method_demo;

import com.kgyam.dependency.injection.domain.UserManger;
import com.kgyam.domain.SuperUser;
import com.kgyam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.*;

/**
 * 方法注入方式： 1.在xml配置中配置look-up方法,方法中可以return null,container会做增强处理
 * 2.实现ApplicationContextAware回调ApplicationContext的getBean方法依赖查找bean。缺点是实现了Aware接口,跟Spring了做了强依赖
 * 3.使用@Lookup注解,方法中可以return null,container会做增强处理,注意注入的Bean对象不能是通过@Bean方式配置[官网说不行，实现测试可行]
 *
 * @author kg yam
 * @date 2021-01-28 17:46
 * @since
 */
@ComponentScan("com.kgyam.dependency")
@Configuration
public class DependencyMethodInjectionDemo {

    @Autowired
    private UserManger userManger;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (DependencyMethodInjectionDemo.class);
        BeanDefinitionReader reader = new XmlBeanDefinitionReader (applicationContext);
//        reader.loadBeanDefinitions ("./META-INF/dependency-method-injection-context.xml");
        applicationContext.refresh ();
        DependencyMethodInjectionDemo demo = applicationContext.getBean (DependencyMethodInjectionDemo.class);
        UserManger manger = demo.userManger;
//        User u1 = manger.createUser ();
//        User u2 = manger.createUserByAnnotation ();
//        System.out.println (u1.hashCode ());
//        System.out.println (u2.hashCode ());

//        UserManger xmlManger = applicationContext.getBean ("user-manger-xml", UserManger.class);
//        User u3 = xmlManger.createUserXml ();
//        System.out.println ("u3:" + u3.hashCode ());

        SuperUser u4 = manger.createUserByBeanAnnotation ();
        System.out.println ("u4:" + u4.toString ());
        applicationContext.close ();
    }


    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public SuperUser superUser() {
        SuperUser user = new SuperUser ();
        user.setName ("superuser");
        return user;
    }
}
