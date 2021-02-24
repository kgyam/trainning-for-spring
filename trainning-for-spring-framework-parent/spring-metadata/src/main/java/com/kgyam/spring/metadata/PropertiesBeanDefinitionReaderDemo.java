package com.kgyam.spring.metadata;

import com.kgyam.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 * 加载以Properties方式配置的元信息
 * 这里的资源文件使用ResourceLoader方式加载，
 * 然后以PropertiesBeanDefinitionReader解析
 * <p>
 * @see PropertiesBeanDefinitionReader
 */
public class PropertiesBeanDefinitionReaderDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory ();
        //读取Properties元信息的reader
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader (beanFactory);

        //资源加载器，用于加载资源
        ResourceLoader resourceLoader = new DefaultResourceLoader ();

        //用资源加载器加载指定路径资源
        Resource resource = resourceLoader.getResource ("classpath:META-INF/user-bd.properties");

        //转换配置文件的编码格式,防止中文输出乱码
        //EncodedResource是转编码格式的resource对象
        //可防止配置文件与代码编码格式不一导致的防止乱码问题
        EncodedResource encodedResource = new EncodedResource (resource, "GBK");
        reader.loadBeanDefinitions (encodedResource);

        User user = beanFactory.getBean ("user", User.class);
        System.out.println (user);
    }
}
