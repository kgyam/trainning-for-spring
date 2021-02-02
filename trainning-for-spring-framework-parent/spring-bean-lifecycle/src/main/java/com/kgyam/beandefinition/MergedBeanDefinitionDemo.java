package com.kgyam.beandefinition;

import com.kgyam.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 阅读BeanDefinition合并过程源码示例
 * <p>
 * 源码入口：
 * main->AbstractApplicationContext#refresh->AbstractApplicationContext#invokeBeanFactoryPostProcessors
 * ->PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors->DefaultListableBeanFactory#getBeanNamesForType
 * ->DefaultListableBeanFactory#doGetBeanNamesForTypes->AbstractBeanFactory#getMergedLocalBeanDefinition
 * <p>
 * getMergedLocalBeanDefinition是合并本地bean definition的过程【非层次性】
 * <p>
 * <p>
 * <p>
 * 基本逻辑：
 * 将GenericBeanDefinition转换成RootBeanDefinition的过程。
 * 原BeanDefinition递归将parent的BeanDefinition转换成RootBeanDefinition【然后return parent RBD的副本实例对象】。
 * 最后将自身的属性override到parent的RBD的副本上并转换为自身的RBD。
 *
 * 猜测：
 * 所有BeanDefinition的最终状态就是RootBeanDefinition
 */
public class MergedBeanDefinitionDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("classpath:META-INF/merged-bean-definition-context.xml");
        applicationContext.refresh();
        User u = applicationContext.getBean(User.class);
        System.out.println(u);
        applicationContext.close();
    }

}
