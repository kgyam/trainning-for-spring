package com.kgyam.dependency.injection.source_code_analysis;

import com.kgyam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 注解驱动的依赖注入源码学习入口
 * <p>
 * 源码入口: DefaultListableBeanFactory#resolveDependency
 * 这里暂时只罗列一些关键点方法
 *
 * doResolveDependency() 是处理常规依赖的方法，前面都是处理Optional和ObjectFactory、JSR330(@Injection)的处理逻辑
 * 主要细看doResolveDependency.
 *
 * Object result = getAutowireCandidateResolver().getLazyResolutionProxyIfNecessary(descriptor, requestingBeanName);
 * 猜测是延迟加载的代理对象，如果不为空直接返回，为空调用doResolveDependency()
 *
 * 1. doResolveDependency处理的是集合类型以及单个类型的处理
 *          1.1 Object multipleBeans = resolveMultipleBeans(descriptor, beanName, autowiredBeanNames, typeConverter)
 *          如果集合类型处理逻辑，如果有返回直接return
 *
 *         1.2 findAutowireCandidates(beanName, type, descriptor)找到容器里面的那个Bean。
 *
 *         1.3 determineAutowireCandidate(matchingBeans, descriptor); 决定自动注入的那个bean
 *                  1.3.1 determinePrimaryCandidate(candidates, requiredType);
 *                  这个是找到设定了primary的那个bean，如果出现多个就是抛出NoUniqueBeanDefinitionException异常
 *                  如果没有primary设定和priority,执行fallback方案，就是找到field的name和beanName一样的那个Bean返回
 *
 *
 *
 * <p>
 * <p>
 * DependencyDescriptor: 依赖描述符用于记录被注入的依赖的元信息，这些依赖是被作为构造器注入、方法注入、参数注入的方式注入的
 * private final Class<?> declaringClass; 被注入的那个class类型，并非注入bean的class
 * <p>
 * private String methodName; 通过方法注入的方法名称
 * <p>
 * private Class<?>[] parameterTypes; 通过构造器注入的构造器参数类型
 * <p>
 * private int parameterIndex; 构造器参数索引
 * <p>
 * private String fieldName; 通过属性注入的那个属性name
 * <p>
 * private final boolean required; BeanDefinition中的required
 * <p>
 * private final boolean eager; 是否延迟加载 如果lazy=true这个属性就是false
 * <p>
 * private int nestingLevel = 1; 嵌套层级
 * <p>
 * private Class<?> containingClass; 容器的class类型
 * <p>
 * private transient volatile ResolvableType resolvableType;
 * <p>
 * private transient volatile TypeDescriptor typeDescriptor;
 *
 *      InjectionPoint:spring 4.3之后,
 *          protected MethodParameter methodParameter; 被注入的方法参数属性
 *          protected Field field; 被注入的Field属性
 *          private volatile Annotation[] fieldAnnotations; 被注入的字段属性的注解
 *
 * @author kg yam
 * @date 2021-01-12 11:57
 * @since
 */
public class SourceCodeAnalysisDemo {

    @Autowired
    private User user;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader (applicationContext);
        beanDefinitionReader.loadBeanDefinitions ("classpath:META-INF/dependency-injection-type-context.xml");
        applicationContext.register (SourceCodeAnalysisDemo.class);
        applicationContext.refresh ();
        SourceCodeAnalysisDemo demo = applicationContext.getBean (SourceCodeAnalysisDemo.class);
        System.out.println (demo.user);
        applicationContext.close ();
    }
}
