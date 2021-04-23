package com.kgyam.event.springEvent;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 依赖注入ApplicationEventPublisher的三种方式：
 * <p>
 * 1. 通过注解@Autowired或者@Injection
 * 2. 通过ApplicationEventPublisherAware接口回调
 * <p>
 * 3. 通过ApplicationContextAware接口回调，因为ApplicationContext继承了ApplicationEventPublisher
 * 所以ApplicationContext本身具备了发布事件的能力
 * <p>
 * <p>
 * Aware接口的回调有一定顺序性，可以参考源码：
 * {@link org.springframework.context.support.ApplicationContextAwareProcessor#invokeAwareInterfaces(java.lang.Object)}
 *
 * @author kg yam
 * @date 2021-04-21 16:36
 * @since
 */
public class InjectionPublisherDemo implements ApplicationEventPublisherAware, ApplicationContextAware {

    @Autowired
    private ApplicationEventPublisher publisher;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (InjectionPublisherDemo.class);
        applicationContext.addApplicationListener (new ApplicationListener<MyApplicationEvent> () {
            @Override
            public void onApplicationEvent(MyApplicationEvent event) {
                System.out.println (event);
            }
        });
        applicationContext.refresh ();

        InjectionPublisherDemo demo = applicationContext.getBean (InjectionPublisherDemo.class);
        demo.publisher.publishEvent (new MyApplicationEvent ("injection"));

        applicationContext.close ();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println ("ApplicationEventPublisherAware#setApplicationEventPublisher");
        applicationEventPublisher.publishEvent (new MyApplicationEvent ("setApplicationEventPublisher"));
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println ("ApplicationContextAware#setApplicationContext");
        applicationContext.publishEvent (new MyApplicationEvent ("setApplicationContext"));
    }


    static class MyApplicationEvent extends ApplicationEvent {
        /**
         * Create a new {@code ApplicationEvent}.
         *
         * @param source the object on which the event initially occurred or with
         *               which the event is associated (never {@code null})
         */
        public MyApplicationEvent(Object source) {
            super (source);
        }
    }
}
