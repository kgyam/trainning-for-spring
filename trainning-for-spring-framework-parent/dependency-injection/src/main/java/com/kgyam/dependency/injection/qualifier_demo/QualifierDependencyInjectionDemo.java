package com.kgyam.dependency.injection.qualifier_demo;

import com.kgyam.dependency.injection.annotations.CustomGroup;
import com.kgyam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collection;

/**
 * 使用Qualifier的示例
 * <p>
 * 使用Qualifier能够精确指定BeanName
 * 同时还能对Bean进行分组
 * {@link Qualifier}
 * <p>
 * <p>
 * 使用自定义注解[标记上Qualifier的],能通过自定义注解对注入对象进行分组。
 * 只注入标记了自定义注解的Bean对象。参考{@link CustomGroup}
 *
 * @author kg yam
 * @date 2021-01-08 16:12
 * @since
 */
public class QualifierDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User user2;

    @Autowired
    @Qualifier
    private Collection<User> userCollection;

    @Autowired
    @CustomGroup
    private User[] customGroupUser;

    private static final String XML_LOCATION = "classpath:META-INF/dependency-setter-injection-context-2.xml";

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (QualifierDependencyInjectionDemo.class);
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader (applicationContext);
        beanDefinitionReader.loadBeanDefinitions (XML_LOCATION);
        applicationContext.refresh ();
        QualifierDependencyInjectionDemo demo = applicationContext.getBean (QualifierDependencyInjectionDemo.class);
        getBeanByQualifier (demo);
        getBeanByQualifierGroup (demo);
        getBeanByCustomGroupAnnotation (demo);
        applicationContext.close ();
    }


    /**
     * 通过@Qualifier指定BeanName获取Bean对象
     *
     * @param demo
     */
    static void getBeanByQualifier(QualifierDependencyInjectionDemo demo) {
        /*
        user no @Qualifier:SuperUser{superId='super_id_123'} User{name='kgyam', age=18}
        这里不用@Qualifier指定beanName返回的是superUser的Bean，因为superUser设定了primary
         */
        System.out.println ("user no @Qualifier:" + demo.user);

        /*
        user by @Qualifier:User{name='kgyam', age=18}
        使用@Qualifier指定beanName后返回的是beanName为user的Bean，
        这里是可以通过@Qualifier让程序更加精确获取指定beanName的Bean
         */
        System.out.println ("user by @Qualifier:" + demo.user2);
    }


    /**
     * 实现用@Qualifier方式对Bean对象进行分组
     *
     * @param demo
     */
    static void getBeanByQualifierGroup(QualifierDependencyInjectionDemo demo) {
        /*
        user group by @Qualifier:[User{name='user3', age=null}, User{name='user3', age=null}]

        我们可以通过在构建Bean对象的方法上加上@Qualifier,可以实现逻辑分组。
        在Collection获取User的时候只会获取标记了@Qualifier的User Bean

        -----------------------------------------------------------
        使用了@CustomGroup后,这里同时会注入了@CustomGroup标记的Bean
        因为@CustomGroup是继承自@Qualifier
        所以也会把@CustomGroup标记的对象注入到集合中

         */
        System.out.println ("user group by @Qualifier:" + demo.userCollection);
    }


    static void getBeanByCustomGroupAnnotation(QualifierDependencyInjectionDemo demo) {

        /*
        user group by @CustomGroup:[User{name='user5', age=null}, User{name='user6', age=null}]
        这里获取的是使用了自定义注解@CustomGroup的User Bean
         */
        System.out.println ("user group by @CustomGroup:" + Arrays.toString (demo.customGroupUser));
    }


    @Bean
    @Qualifier
    public User user3() {
        User user = new User ();
        user.setName ("user3");
        return user;
    }

    @Bean
    @Qualifier
    public User user4() {
        User user = new User ();
        user.setName ("user3");
        return user;
    }


    @Bean
    @CustomGroup
    public User user5() {
        User user = new User ();
        user.setName ("user5");
        return user;
    }

    @Bean
    @CustomGroup
    public User user6() {
        User user = new User ();
        user.setName ("user6");
        return user;
    }
}
