package com.kgyam.event.springEvent;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 注册监听器方式：1.ConfigurableApplicationContext#addApplicationListener
 * 2.注册为Spring Bean
 * 3.通过@EventListener {@link AnnotationSpringEventDemo}
 *
 * @author kg yam
 * @date 2021-04-20 16:09
 * @since
 */
public class SpringEventDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        /*
        通过org.springframework.context.annotation.AnnotationConfigRegistry.register
          将其注册为Spring Bean执行。

          这里注意虽然先注册为Spring Bean，但执行顺序后于addApplicationListener加入的监听器执行
         */
        applicationContext.register (MyApplicationListener.class);
        /*
        通过bd注册的监听器bean
         */
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition (MyApplicationListener.class);
        beanDefinitionBuilder.addPropertyValue ("name", "bd-my-app-li");
        applicationContext.registerBeanDefinition ("bd-my-app-li", beanDefinitionBuilder.getBeanDefinition ());


        MyApplicationListener myApplicationListener = new MyApplicationListener ();
        myApplicationListener.setName ("init-my-app-li");
        applicationContext.addApplicationListener (myApplicationListener);

        applicationContext.refresh ();



          /*
        1. 通过ConfigurableApplicationContext的API注册
        org.springframework.context.ConfigurableApplicationContext.addApplicationListener
         */
        applicationContext.addApplicationListener (new ApplicationListener<ApplicationEvent> () {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println ("收到Application Event:" + event);
            }
        });

        applicationContext.publishEvent (new ApplicationEvent ("innerApplicationEvent") {
            @Override
            public String getSource() {
                return (String) super.getSource ();
            }
        });

        applicationContext.close ();
    }


    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
        private String name = "DefaultMyApplicationListener";

        public MyApplicationListener() {
        }

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.out.println ("当前对象name:" + name + "开始执行");
            System.out.println ("MyApplicationListener#onApplicationEvent event:" + event);
            System.out.println ("当前对象name:" + name + "执行完成");
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
