package com.kgyam.dependency.lookup;


import com.kgyam.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 层级依赖查找示例
 * <p>
 * 这里的层级指的是父子容器的层级
 */
public class HierarchicalDependencyLookupDemo {


    public static void main(String[] args) {
        //public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        /*
        注册当前类作为配置类
         */
        applicationContext.register(HierarchicalDependencyLookupDemo.class);
        ConfigurableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.printf("beanFactory:[%s]\n", beanFactory);
        System.out.printf("parentBeanFactory:[%s]\n", beanFactory.getParentBeanFactory());
        /*
        构建父beanFactory
         */
        BeanFactory parent = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-lookup-context.xml");
        beanFactory.setParentBeanFactory(parent);

        /*
        启动容器
         */
        applicationContext.refresh();
        System.out.printf("parentBeanFactory:[%s]\n", beanFactory.getParentBeanFactory());


        /*
        自定义的一个双亲委派实现
         */
        System.out.println("parentBeanFactory customContainsLocalBean result:" + customContainsLocalBean(parent, "user"));
        System.out.println("beanFactory customContainsLocalBean result:" + customContainsLocalBean(beanFactory, "user"));



        /*
        子容器和父容器都有user这个Bean,这里返回的是子容器的user Bean
         */
        User user = (User) beanFactory.getBean("user");
        System.out.println("User :" + user);

        /*
        源码是先找到自己，没有再找父beanFactory
         */
        beanFactory.containsBean("user");
        System.out.println("parentBeanFactory containsLocalBean result:" + HierarchicalBeanFactory.class.cast(parent).containsLocalBean("user"));

        /*
        如果父容器有user，子容器没有，返回false。
        containsLocalBean只会找自己容器的是否存在这个bean
         */
        System.out.println("beanFactory containsLocalBean result:" + HierarchicalBeanFactory.class.cast(beanFactory).containsLocalBean("user"));
        applicationContext.close();
    }


    @Bean
    public User user() {
        User user = new User();
        user.setName("kkgg");
        user.setAge(30);
        return user;
    }

    /**
     * 自定义查找容器bean
     *
     * @param beanFactory
     * @param beanName
     * @return
     */
    static boolean customContainsLocalBean(BeanFactory beanFactory, String beanName) {
        /*
        判断beanFactory是否为空且是否为HierarchicalBeanFactory
        因为getParentBeanFactory是HierarchicalBeanFactory接口定义的方法
         */
        if (beanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory hierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(beanFactory);
            /*
            双亲委派，首先从父容器中找是否存在该beanName的bean
             */
            if (hierarchicalBeanFactory.getParentBeanFactory() != null) {
                /*
                如果从父容器找到beanName,直接返回
                否则再查找自己是否有该beanName的bean
                 */
                if (customContainsLocalBean(hierarchicalBeanFactory.getParentBeanFactory(), beanName)) {
                    return true;
                }
            }
            //判断beanName在当前beanFactory下是否存在
            return hierarchicalBeanFactory.containsLocalBean(beanName);
        }
        return false;
    }

}
