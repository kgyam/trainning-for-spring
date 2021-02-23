package com.kgyam.spring.metadata;

import com.kgyam.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

public class PropertiesBeanDefinitionReaderDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);

        //用于加载resource资源
        ResourceLoader resourceLoader = new DefaultResourceLoader();

        //用资源加载器加载指定路径资源
        Resource resource = resourceLoader.getResource("classpath:META-INF/user-bd.properties");

        //转换配置文件的编码格式,防止中文输出乱码
        EncodedResource encodedResource = new EncodedResource(resource, "GBK");
        reader.loadBeanDefinitions(encodedResource);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
