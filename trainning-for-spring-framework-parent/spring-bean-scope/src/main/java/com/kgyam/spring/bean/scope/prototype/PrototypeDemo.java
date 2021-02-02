package com.kgyam.spring.bean.scope.prototype;

import com.kgyam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * Spring Bean Scope为Prototype 示例
 * 原型模式
 * 不论是依赖注入还是依赖查找，都会生成一个全新的Bean对象
 * <p>
 * scope为prototype类型的bean，容器无法管理这些bean完整的生命周期。
 * 这些Bean能进行初始化的方法回调，但是无法进行销毁的方法回调(无论是@PreDestroy还是DisposableBean)
 */

public class PrototypeDemo {
// TODO: 2021/1/25 源码分析，为何生命周期不完整
    /**
     * 注入scope为prototype的bean
     */
    @Autowired
    @Qualifier("user")
    private User prototype_user1;

    @Autowired
    @Qualifier("user")
    private User prototype_user2;

    @Autowired
    @Qualifier("user")
    private User prototype_user3;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (PrototypeDemo.class);
        applicationContext.refresh ();
        //xml设置scope
        setScopeByXml (applicationContext);
        //从BeanDefinition设置scope
        setScopeByBeanDefinition (applicationContext);
        //利用@Scope设置
        injectionPrototypeBean (applicationContext);
        applicationContext.close ();
    }


    /**
     * 使用注解方式定义Bean Scope
     *
     * @return
     */
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User user() {
        User user = new User ();
        user.setName ("prototype-user");
        return user;
    }


    private static void injectionPrototypeBean(ApplicationContext applicationContext) {
        PrototypeDemo demo = applicationContext.getBean (PrototypeDemo.class);
        System.out.println ("injectionPrototypeBean,injection bean is the same:" + (demo.prototype_user1 == demo.prototype_user2));
        System.out.println ("injectionPrototypeBean,injection bean is the same:" + (demo.prototype_user2 == demo.prototype_user3));
        System.out.println ("injectionPrototypeBean,injection bean is the same:" + (demo.prototype_user1 == demo.prototype_user3));
        System.out.println ("#############################################################");
    }

    /**
     * 使用BeanDefinition定义Bean Scope
     *
     * @return
     */
    private static void setScopeByBeanDefinition(ApplicationContext applicationContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition (User.class);
        builder.addPropertyValue ("name", "bd-user")
                .setScope (BeanDefinition.SCOPE_PROTOTYPE);
        if (applicationContext instanceof AnnotationConfigApplicationContext) {
            String beanName = "registry-bd-prototype-user";
            ((AnnotationConfigApplicationContext) applicationContext).registerBeanDefinition (beanName, builder.getBeanDefinition ());
            User u1 = applicationContext.getBean (beanName, User.class);
            User u2 = applicationContext.getBean (beanName, User.class);
            System.out.println ("setScopeByXml,lookup bean is the same:" + (u1 == u2));
            System.out.println ("#############################################################");

        }
    }


    /**
     * 读取xml配置
     *
     * @param
     */
    private static void setScopeByXml(ApplicationContext applicationContext) {
        if (applicationContext instanceof BeanDefinitionRegistry) {
            BeanDefinitionReader reader = new XmlBeanDefinitionReader ((BeanDefinitionRegistry) applicationContext);
            reader.loadBeanDefinitions ("META-INF/bean-prototype-context.xml");
            User u1 = applicationContext.getBean ("prototype-xml-user", User.class);
            User u2 = applicationContext.getBean ("prototype-xml-user", User.class);
            System.out.println ("setScopeByXml,lookup bean is the same:" + (u1 == u2));
            System.out.println ("###########################################################");
        }
    }
}
