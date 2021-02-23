package com.kgyam.spring.metadata;

import com.kgyam.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ObjectUtils;

/**
 * Bean配置元信息示例
 * <p>
 * bean definition是用于描述bean实例，
 * 如property value、property reference、构造器参数、初始化方法、销毁方法、scope等都是从这里获取的
 * <p>
 * bean factory利用bd构建bean实例
 * <p>
 * beanDefinition作用
 */
public class BeanConfigurationMetadataDemo {

    public static void main(String[] args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "kgyam")
                .addPropertyValue("region", "GUANGZHOU");

        AbstractBeanDefinition bd = beanDefinitionBuilder.getBeanDefinition();
        //设置BeanDefinition来源，这个方法只有AbstractBeanDefinition才有
        bd.setSource(BeanConfigurationMetadataDemo.class);
        bd.setAttribute("attribute-name", "大力爸爸");

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals("user", beanName) && bean.getClass().equals(User.class)) {
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);

                    if (bd.getSource().equals(BeanConfigurationMetadataDemo.class)) {
                        System.out.println("beanName: " + beanName);
                        System.out.println("attribute-name: " + bd.getAttribute("attribute-name"));
                    }
                }
                return bean;
            }
        });
        beanFactory.registerBeanDefinition("user", bd);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
