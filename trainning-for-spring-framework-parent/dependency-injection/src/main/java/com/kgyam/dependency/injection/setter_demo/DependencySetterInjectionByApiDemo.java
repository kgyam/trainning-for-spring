package com.kgyam.dependency.injection.setter_demo;

import com.kgyam.dependency.injection.domain.UserHolder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * @author kg yam
 * @date 2021-01-07 16:36
 * @since 使用Spring Api的方式实现setter注入
 * <p>
 * 同时这是最核心的方法，xml和注解方式底层实现就是通过这种方式进行
 * beanDefinitionBuilder#addPropertyReference() 设置property从而设定setter注入
 */
public class DependencySetterInjectionByApiDemo {
    private static final String XML_LOCATION = "classpath:META-INF/dependency-setter-injection-context-2.xml";

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory ();

        /*
        创建BeanDefinitionReader读取xml配置
         */
        BeanDefinitionReader reader = new XmlBeanDefinitionReader (beanFactory);
        reader.loadBeanDefinitions (XML_LOCATION);

        /*
        这里使用BeanDefinitionRegistry的registryBeanDefinition方式注册beanDefinition
        DefaultListableBeanFactory实现了BeanDefinitionRegistry,所以直接使用即可
         */
        beanFactory.registerBeanDefinition ("userHolder", createBeanDefiniton ());
        UserHolder userHolder = beanFactory.getBean ("userHolder", UserHolder.class);
        System.out.println (userHolder);

    }


    /**
     * 创建UserHolder的BeanDefinition
     *
     * @return
     */
    static BeanDefinition createBeanDefiniton() {
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition (UserHolder.class);
        //通过property设定setter注入
        beanDefinitionBuilder.addPropertyReference ("user", "user");
        return beanDefinitionBuilder.getBeanDefinition ();
    }

}
