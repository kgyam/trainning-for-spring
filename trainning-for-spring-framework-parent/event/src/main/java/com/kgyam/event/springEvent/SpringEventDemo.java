package com.kgyam.event.springEvent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 *
 * 注册监听器方式：1.ConfigurableApplicationContext#addApplicationListener
 * 2.注册为Spring Bean
 * 3.通过@EventListener {@link AnnotationSpringEventDemo}
 *
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
                return (String)super.getSource ();
            }
        });

        applicationContext.close ();
    }


    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.out.println ("MyApplicationListener#onApplicationEvent event:"+event);
        }
    }
}
