package com.kgyam.event.customizedSpringEvent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 自定义事件步骤：
 * 1.自定义事件，继承ApplicationEvent
 * 2.自定义事件监听器，实现ApplicationListener接口
 * 3.注册监听器&发布事件
 *
 * @author kg yam
 * @date 2021-04-21 16:30
 * @since
 */
public class Demo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (Demo.class);
        applicationContext.addApplicationListener (new MyEventListener ());
        applicationContext.refresh ();
        applicationContext.close ();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent (new MyApplicationEvent ("hello,setApplicationEventPublisher"));
    }
}
