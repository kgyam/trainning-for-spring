package com.kgyam.dependency.source;

import com.kgyam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 常规方式
 * 通过BeanDefinitionRegistry#registerBeanDefinition
 *
 *
 * @author kg yam
 * @date 2021-01-20 16:14
 * @since
 */
public class BeanDefinitionRegistrySourceDemo {

    @Autowired
    private User user;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();

        applicationContext.register (BeanDefinitionRegistrySourceDemo.class);
        DefaultListableBeanFactory beanFactory = applicationContext.getDefaultListableBeanFactory ();
        beanFactory.registerBeanDefinition ("bd", buildBeanDefinition ());
        applicationContext.refresh ();
        BeanDefinitionRegistrySourceDemo demo = beanFactory.getBean (BeanDefinitionRegistrySourceDemo.class);
        System.out.println (demo.user);
        applicationContext.close ();
    }


    static BeanDefinition buildBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition (User.class);
        return beanDefinitionBuilder.addPropertyValue ("name", "bd-user")
                .addPropertyValue ("age", 20).getBeanDefinition ();
    }
}
