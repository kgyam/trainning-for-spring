package com.kgyam.domain;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 针对Bean的初始化、销毁
 */
public class SubUser implements InitializingBean, DisposableBean {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    //################################################################################
    //###################################初始化方法#####################################
    //################################################################################


    /**
     * 通过@Lazy延迟加载设定的初始化方法
     */
    public void lazyInit() {
        System.out.println("lazy init by @Lazy");
    }

    /**
     * Xml配置的初始化方法
     */
    public void init() {
        System.out.println("init by xml method init config");
    }

    /**
     * 通过BeanDefinition设定的初始化方法
     */
    public void initByBeanDefinition() {
        System.out.println("init by BeanDefinitionBuilder#setInitMethodName()");
    }

    /**
     * 通过@Bean配置设定的初始化方法
     */
    public void initByAnnotation() {
        System.out.println("init by @Bean init-method");
    }

    /**
     * 通过@PostConstruct设定的初始化方法
     */
    @PostConstruct
    public void initByPostConstruct() {
        System.out.println("init method by  @PostConstruct");
    }


    /**
     * 实现InitializingBean接口的初始化方法
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(" init by InitializingBean#afterPropertiesSet()");
    }

    //################################################################################
    //###################################初始化方法#####################################
    //################################################################################


    //################################################################################
    //###################################销毁方法######################################
    //################################################################################

    /**
     * 通过@PreDestroy设定的销毁方法
     */
    @PreDestroy
    public void initByPreDestroy() {
        System.out.println("destroy method by @PreDestroy");
    }

    /**
     * 实现DisposableBean接口的销毁方法
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("destroy by DisposableBean#destroy()");
    }

    /**
     * 通过Xml配置的destroy-method设定销毁方法
     */
    public void destroyByXml() {
        System.out.println("destroy by xml destroy-method");
    }

    public void destroyByBeanAnnotation() {
        System.out.println("destroy by @Bean destroy-method");

    }

    public void destroyByBeanDefinition() {
        System.out.println("destroy by bean definition");
    }


    //################################################################################
    //###################################销毁方法######################################
    //################################################################################


    @Override
    public String toString() {
        return "SubUser{" +
                "type='" + type + '\'' +
                "} " + super.toString();
    }

}
