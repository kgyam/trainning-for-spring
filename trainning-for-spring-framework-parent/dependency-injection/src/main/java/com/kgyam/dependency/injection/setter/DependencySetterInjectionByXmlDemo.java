package com.kgyam.dependency.injection.setter;

import com.kgyam.dependency.injection.domain.UserHolder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 通过xml配置实现的[手动]setter注入示例
 */
public class DependencySetterInjectionByXmlDemo {

    private static final String XML_LOCATION = "classpath:META-INF/dependency-setter-injection-context.xml";

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        /**
         * 定义beanDefinition读取器，并设定具体xml路径
         */
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(XML_LOCATION);
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);
    }
}
