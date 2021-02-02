package com.kgyam.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;

/**
 * 所有Aware接口的示例
 * <p>
 * BeanNameAware、BeanClassLoaderAware、BeanFactoryAware 是BeanFactory和ApplicationContext都可以调用的接口
 * <p>
 * 其他只有通过ApplicationContext启动才能进行回调
 *
 *
 * BeanNameAware、BeanClassLoaderAware、BeanFactoryAware的方法调用栈：
 *
 * main->AbstractApplicationContext#refresh->AbstractApplicationContext#finshBeanFactoryInitialization
 * ->DefaultListableBeanFactory#preInstantiateSingleton->AbstractBeanFactory#getBean->AbstractBeanFactory#doGetBean
 * ->DefaultSingletonBeanRegistry#getSingleton->AbstractAutowireCapableBeanFactory#createBean
 * ->AbstractAutowireCapableBeanFactory#doCreateBean->AbstractAutowireCapableBeanFactory#initializeBean
 * ->AbstractAutowireCapableBeanFactory#invokeAwareMethods->三个Aware接口的方法调用
 *
 *
 * 其他Aware接口的调用栈：
 * main->AbstractApplicationContext#refresh->AbstractApplicationContext#finshBeanFactoryInitialization
 * ->DefaultListableBeanFactory#preInstantiateSingleton->AbstractBeanFactory#getBean->AbstractBeanFactory#doGetBean
 * ->DefaultSingletonBeanRegistry#getSingleton->AbstractAutowireCapableBeanFactory#createBean
 * ->AbstractAutowireCapableBeanFactory#doCreateBean->AbstractAutowireCapableBeanFactory#initializeBean
 * ->AbstractAutowireCapableBeanFactory#applyBeanPostProcessorBeforeInitialization
 * ->ApplicationContextAwareProcessor#postProcessBeforeInitialization->ApplicationContextAwareProcessor#invokeAwareInterfaces
 * ->Aware接口的方法调用
 *
 *
 * 为什么其他Aware接口只有BeanFactory的情况不能调用？
 * 因为ApplicationContextAwareProcessor这个BeanPostProcessor
 * 是在AbstractApplicationContext的prepareBeanFactory中注册到容器中的。
 * 所以执行AC的方法是没有这个处理器，从而不会执行这些Aware接口。
 * {@link org.springframework.context.support.ApplicationContextAwareProcessor}
 * {@link org.springframework.context.support.AbstractApplicationContext}
 *
 * @author kg yam
 * @date 2021-02-02 16:38
 * @since
 */
public class AwareInterfaceDemo implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware
        , EnvironmentAware, EmbeddedValueResolverAware, ResourceLoaderAware, ApplicationEventPublisherAware
        , MessageSourceAware, ApplicationContextAware {

    private String beanName;

    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    private Environment environment;

    private StringValueResolver stringValueResolver;

    private ResourceLoader resourceLoader;

    private ApplicationEventPublisher applicationEventPublisher;

    private MessageSource messageSource;

    private ApplicationContext applicationContext;


    /**
     * EnvironmentAware接口方法
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * BeanNameAware接口方法
     *
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        System.out.println ("setBeanName");
        this.beanName = name;
    }

    /**
     * BeanClassLoaderAware 接口方法
     *
     * @param classLoader
     */
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println ("setBeanClassLoader");
        this.classLoader = classLoader;
    }

    /**
     * BeanFactoryAware 接口方法
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println ("setBeanFactory");
        this.beanFactory = beanFactory;
    }

    /**
     * EmbeddedValueResolverAware 接口方法
     *
     * @param resolver
     */
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println ("setEmbeddedValueResolver");
        this.stringValueResolver = resolver;
    }

    /**
     * ResourceLoaderAware 接口方法
     *
     * @param resourceLoader
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println ("setResourceLoader");
        this.resourceLoader = resourceLoader;
    }


    /**
     * ApplicationEventPublisherAware 接口方法
     *
     * @param applicationEventPublisher
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println ("setApplicationEventPublisher");
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * MessageSourceAware 接口方法
     *
     * @param messageSource
     */
    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println ("setMessageSource");
        this.messageSource = messageSource;
    }

    /**
     * ApplicationContextAware 接口方法
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println ("setApplicationContext");
        this.applicationContext = applicationContext;
    }


    public static void main(String[] args) {
//        executeByBeanFactory ();
        executeByApplicationContext ();
    }


    static void executeByApplicationContext() {
        String location = "classpath:META-INF/aware-interface-context.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext (location);
        AwareInterfaceDemo demo = applicationContext.getBean (AwareInterfaceDemo.class);
        System.out.println (demo.toString ());
    }

    static void executeByBeanFactory() {
        String location = "classpath:META-INF/aware-interface-context.xml";
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory ();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader (beanFactory);
        reader.loadBeanDefinitions (location);

        AwareInterfaceDemo demo = beanFactory.getBean (AwareInterfaceDemo.class);
        System.out.println (demo.toString ());
    }

    @Override
    public String toString() {
        return "AwareInterfaceDemo{" +
                "beanName='" + beanName + '\'' +
                ", classLoader=" + classLoader +
                ", beanFactory=" + beanFactory +
                ", environment=" + environment +
                ", stringValueResolver=" + stringValueResolver +
                ", resourceLoader=" + resourceLoader +
                ", applicationEventPublisher=" + applicationEventPublisher +
                ", messageSource=" + messageSource +
                ", applicationContext=" + applicationContext +
                '}';
    }
}
