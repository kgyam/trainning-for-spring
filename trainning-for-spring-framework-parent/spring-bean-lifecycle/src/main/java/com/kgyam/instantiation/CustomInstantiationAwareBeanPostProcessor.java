package com.kgyam.instantiation;

import com.kgyam.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

public class CustomInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    /**
     * 实例化前置操作，InstantiationAwareBeanPostProcessor接口方法
     * 这里可以做一个拦截，如果返回not null对象。容器直接返回not null对象，
     * 不进行后续操作【实例化、赋值、初始化、销毁】,但会回调BeanPostProcessor#postProcessAfterInitialization
     * <p>
     * main->AbstractApplicationContext#refresh->AbstractApplicationContext#invokeBeanFactoryPostProcessor
     * ->PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessor->AbstractBeanFactory#getBean
     * ->AbstractBeanFactory#doGetBean->DefaultSingletonBeanRegistry#getSingleton->AbstractAutowireCapableBeanFactory#createBean
     * ->AbstractAutowireCapableBeanFactory#resolveBeforeInstantiation->AbstractAutowireCapableBeanFactory#applyBeanPostProcessorBeforeInstantiation
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
//        if ("user".equals(beanName)) {
//        非主流bean生命周期
//            System.out.println("CustomInstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation");
//            User user = new User();
//            user.setName("大力爸爸");
//            return user;
//        }
        return null;
    }


    /**
     * 实例化后置操作
     * <p>
     * main->AbstractApplicationContext#refresh->AbstractApplicationContext#invokeBeanFactoryPostProcessor
     * ->PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessor->AbstractBeanFactory#getBean
     * ->AbstractBeanFactory#doGetBean->DefaultSingletonBeanRegistry#getSingleton->AbstractAutowireCapableBeanFactory#createBean
     * ->AbstractAutowireCapableBeanFactory#doCreateBean->【赋值逻辑】AbstractAutowireCapableBeanFactory#populateBean
     * ->【自定义后置回调】CustomInstantiationAwareBeanPostProcessor#postProcessAfterInstantiation
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if ("user".equals(beanName)) {
            System.out.println("CustomInstantiationAwareBeanPostProcessor#postProcessAfterInstantiation");
        }
        return false;
    }


    /**
     * 初始化完成的后置操作
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("user".equals(beanName)) {
            System.out.println("CustomInstantiationAwareBeanPostProcessor#postProcessAfterInitialization");
        }
        return bean;
    }
}
