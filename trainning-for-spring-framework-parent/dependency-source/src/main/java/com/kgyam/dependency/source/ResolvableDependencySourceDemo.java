package com.kgyam.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Resolve Dependency作为依赖来源示例
 * <p>
 * 通过ConfigurableListableBeanFactory#registerResolvableDependency解析到容器中
 * 这种对象的特点：
 * 1. 无生命周期管理（无法回调实例化、销毁方法）
 * 2. 无法实现延迟加载
 * 3. 无法通过依赖查找
 * <p>
 * <p>
 * AbstractApplicationContext#prepareBeanFactory中有调用这个方法:
 * <p>
 * {@link AbstractApplicationContext}
 * beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
 * beanFactory.registerResolvableDependency(ResourceLoader.class, this);
 * beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
 * beanFactory.registerResolvableDependency(ApplicationContext.class, this);
 *
 * @author kg yam
 * @date 2021-01-20 14:49
 * @since
 */
public class ResolvableDependencySourceDemo {
    @Autowired
    private String value;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory ();
        //ConfigurableListableBeanFactory#registerResolvableDependency(Class<?> dependencyType, @Nullable Object autowiredValue)
        beanFactory.registerResolvableDependency (String.class, "resolvable-dependency-str");
        applicationContext.register (ResolvableDependencySourceDemo.class);
        applicationContext.refresh ();
        dependencyInjection (beanFactory);
        dependencyLookup (beanFactory);
        applicationContext.close ();
    }


    /**
     * 依赖注入方式
     *
     * @param beanFactory
     */
    private static void dependencyInjection(BeanFactory beanFactory) {
        ResolvableDependencySourceDemo demo = beanFactory.getBean (ResolvableDependencySourceDemo.class);
        System.out.println (demo.value);
    }

    /**
     * 依赖查找方式 无法获取resolve dependency
     *
     * @param beanFactory
     */
    private static void dependencyLookup(BeanFactory beanFactory) {
        try {
            String value = beanFactory.getBean (String.class);
            System.out.println (value);
        } catch (Exception e) {
            System.err.println ("resolve dependency无法通过依赖查找数据");
            System.err.println (e);
        }
    }


}
