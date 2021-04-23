package com.kgyam.event.springEvent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

/**
 * @author kg yam
 * @date 2021-04-21 14:22
 * @since
 */
public class ApplicationEventPublisherDemo implements ApplicationEventPublisherAware {

    /**
     * 使用注解形式无法监听到这两个事件的发布 原因未明
     * @param applicationEventPublisher
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println ("setApplicationEventPublisher");
        //推送ApplicationEvent
        //onApplicationEvent:com.kgyam.event.springEvent.ApplicationEventPublisherDemo$1[source=hello,world]
        applicationEventPublisher.publishEvent (new ApplicationEvent ("hello,world") {
        });

        //推送Object，最终会被包装成PayloadApplicationEvent
        //onApplicationEvent:org.springframework.context.PayloadApplicationEvent[source=org.springframework.context.annotation.AnnotationConfigApplicationContext@1d56ce6a, started on Wed Apr 21 14:32:57 CST 2021]
        applicationEventPublisher.publishEvent ("hello,world 2");
    }


    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (ApplicationEventPublisherDemo.class);
        applicationContext.addApplicationListener (new ApplicationListener<ApplicationEvent> () {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println (" onApplicationEvent:" + event);
            }
        });
        applicationContext.refresh ();
        applicationContext.close ();

    }


//
//    @EventListener
//    @Order(1)
//    public void onApplicationEvent(ApplicationEvent event) {
//        System.out.println ("@EventListener onApplicationEvent:" + event);
//    }
}
