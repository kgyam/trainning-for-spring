package com.kgyam.spring.metadata;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 基于xml方式加载yaml外部化配置信息
 * xml将YamlMapFactoryBean作为bean加载yaml
 * {@link YamlMapFactoryBean}
 *
 * @author kg yam
 * @date 2021-02-25 10:10
 * @since
 */
public class XmlBasedYamlPropertySourceDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory ();
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader (beanFactory);
        beanDefinitionReader.loadBeanDefinitions ("classpath:/META-INF/yml-property-source-content.xml");

        Map<String, Object> yamlMap = beanFactory.getBean ("yamlMap", Map.class);
        System.out.println (yamlMap);
    }

}
