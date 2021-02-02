package com.kgyam.dependency.injection.source_code_analysis;

import com.kgyam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.Collection;

/**
 * 注解驱动的依赖注入源码学习入口(这里不仅包括注入，还有bean实例化的过程)
 * 1 创建BeanDefinition
 * 2 合并RootBeanDefinition[mergedBeanDefinition]
 * 3 创建BeanInstance方法createBean
 * 3.1 doCreateBean真正创建Bean的逻辑方法
 * 3.1.1 createBeanInstance:**实例化**Bean的过程,其中包括工厂方法、构造器、普通实例化
 * 3.1.2 populateBean: 将BeanDefinition中的属性值填充到BeanWrapper的Bean实例中
 * 3.1.2.1 这里有个遍历InstantiationAwareBeanPostProcessor的循环，是Spring留的一个扩展点。
 * 这里是自动装配前的一个操作点，留给我们补充自定义方法让Spring回调对属性做自定义的填充
 * 3.1.2.2 循环遍历BeanPostProcessor后置处理器，其中AutoWiredAnnotationBeanPostProcessor是处理@AutoWired和JSR330的处理逻辑。
 * CommonAnnotationBeanPostProcessor是处理java原注解如@Resource和@PostConstruct等
 * 3.1.2.2.1 findAutowiringMetadata方法找到自动装配的元信息InjectionMetaData【对应这个Bean的注入元信息】
 * 3.1.2.2.2 调用InjectionMetaData的inject方法
 * 3.1.2.2.2--1 遍历InjectedElement【一个元素对应一个被注解标记的注入对象】调用其inject方法。
 * inject方法会创建依赖描述符DependencyDescriptor，以及调用BeanFactory的resolveDependency方法解析依赖。
 * 通过resolveDependency获取到Bean后会通过反射注入到请求注入的Bean中
 * 3.1.2.2.2--2 resolveDependency方法调用doResolveDependency做具体的依赖查找操作，
 * doResolveDependency的重点方法:【determineAutowireCandidate:决定使用哪个Bean对象做注入，这里会判断是否primary等】
 * 3.1.2.3 applyPropertyValues填充值到Bean实例中
 * <p>
 * ===========================================================================================
 * =======================================DependencyDescriptor================================
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
 * <p>
 * InjectionPoint:spring 4.3之后,
 * protected MethodParameter methodParameter; 被注入的方法参数属性
 * protected Field field; 被注入的Field属性
 * private volatile Annotation[] fieldAnnotations; 被注入的字段属性的注解
 * <p>
 * <p>
 * <p>
 * <p>
 * 总结：
 * 1. AutoWiredAnnotationBeanPostProcessor处理@Autowried @Value @Inject(JSR330)的注入
 * 2. CommonAnnotationBeanPostProcessor处理@Resource(JSR250) 、@WebServiceRef 和JPA等注入
 * {@link Resource}
 * {@link Value}
 * {@link Autowired}
 * {@link Inject}
 * {@link javax.xml.ws.WebServiceRef}
 *
 * @author kg yam
 * @date 2021-01-12 11:57
 * @since
 */
public class SourceCodeAnalysisDemo {

    @Autowired
    private User user;

    @Autowired
    private Collection<User> users;

    /**
     * JSR330
     */
    @Inject
    private User user2;


    @Resource
    private User user3;


    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader (applicationContext);
        beanDefinitionReader.loadBeanDefinitions ("classpath:META-INF/dependency-injection-type-context.xml");
        applicationContext.register (SourceCodeAnalysisDemo.class);
        applicationContext.refresh ();
        SourceCodeAnalysisDemo demo = applicationContext.getBean (SourceCodeAnalysisDemo.class);
        System.out.println (demo.user);
        System.out.println (demo.users);
        System.out.println (demo.user2);
        System.out.println (demo.user3);
        applicationContext.close ();
    }
}
