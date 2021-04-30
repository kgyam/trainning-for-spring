package com.kgyam.spring.annotation.attributeOverride;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 同名属性覆盖：
 * OverrideScan有一个basePackages的属性，OverrideScan2也有一个basePackages的属性。
 * 那么OverrideScan2可以覆盖OverrideScan的。
 * 但是这里有个问题，就是不能在OverrideScan2的basePackages直接定义属性值，否则会抛出异常，不知为何
 *
 * 只能在OverrideScan2上定义一个属性package通过@AliasFor映射到OverrideScan2的basePackages，
 * 然后再通过这个映射到OverrideScan的basePackages。
 * @author kg yam
 * @date 2021-04-30 10:00
 * @since
 */
//这种会抛异常 @OverrideScan2 (basePackages =  "com.kgyam.spring.annotation.attributeOverride")
@OverrideScan2 (packages =  "com.kgyam.spring.annotation.attributeOverride")
public class AttributeOverrideDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (AttributeOverrideDemo.class);
        applicationContext.refresh ();
        MyComponent component = applicationContext.getBean (MyComponent.class);
        System.out.println (component);
        applicationContext.close ();
    }
}
