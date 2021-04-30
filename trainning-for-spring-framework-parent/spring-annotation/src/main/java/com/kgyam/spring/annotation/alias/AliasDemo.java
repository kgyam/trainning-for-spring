package com.kgyam.spring.annotation.alias;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 注解的别名化，能作等效作用，例如
 * {@link ComponentScan}的 value和basePackages标记了{@link org.springframework.core.annotation.AliasFor}
 * 后其实两个属性是等效的
 * <p>
 * 除此之外别名带有传递性，例如
 * {@link MyComponentScan2}的myBasePackages2能传递到{@link MyComponentScan}
 * 然后传递到{@link ComponentScan}的basePackages
 *
 * @author kg yam
 * @date 2021-04-29 14:06
 * @since
 */
//@MyComponentScan(myBasePackages = "com.kgyam.spring.annotation.alias")
@MyComponentScan2(myBasePackages2 = "com.kgyam.spring.annotation.alias")
public class AliasDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (AliasDemo.class);
        applicationContext.refresh ();
        MyComponent component = applicationContext.getBean (MyComponent.class);
        System.out.println (component);
        applicationContext.close ();
    }
}
