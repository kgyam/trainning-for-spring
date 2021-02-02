package com.kgyam.beandefinition;

import com.kgyam.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * beanDefinition配置示例
 * 常用的xml配置不再进行练习，这里使用之前没有用过properties
 */
public class BeanDefinitionPropertiesConfigDemo {


    public static void main(String[] args) {

        /*
        properties方式配置bean元信息
         */
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(applicationContext);
        String location="/META-INF/bean-definition-config.properties";
        //将location封装为资源对象
        Resource resource=new ClassPathResource(location);
        //转换配置文件的编码格式
        EncodedResource encodedResource=new EncodedResource(resource,"GBK");
        //只有propertiesBeanDefinitionReader才有读取EncodedResource的方法【非override】,所以对象没有使用接口形式表达
        reader.loadBeanDefinitions(encodedResource);
        applicationContext.refresh();
        User user = applicationContext.getBean(User.class);
        System.out.println(user);
        applicationContext.close();
    }

}
