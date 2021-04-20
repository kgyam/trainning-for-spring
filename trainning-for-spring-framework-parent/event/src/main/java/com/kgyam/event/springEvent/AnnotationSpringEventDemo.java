package com.kgyam.event.springEvent;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 基于注解的事件注册
 *
 * 可以通过@Async异步处理注解，spring会开启一条新线程。这里需要利用@EnableAsync启动异步
 *
 * 事件执行顺序可以通过@Order进行排序 数值越小，优先级越高
 *
 * @author kg yam
 * @date 2021-04-20 16:33
 * @since
 */
@EnableAsync
public class AnnotationSpringEventDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (AnnotationSpringEventDemo.class);
        applicationContext.refresh ();
        applicationContext.start ();
        applicationContext.close ();
    }


    @EventListener
    @Order(1)
    public void onStartEvent1(ContextStartedEvent event) {
        print ("@EventListener @Order(1) onStartedEvent:" + event);
    }


    @EventListener
    @Order(2)
    public void onStartEvent2(ContextStartedEvent event) {
        print ("@EventListener @Order(2) onStartedEvent:" + event);
    }

    @EventListener
    public void onRefreshEvent(ContextRefreshedEvent event) {
        print ("@EventListener onRefreshEvent:" + event);
    }


    @EventListener
    @Async
    public void onRefreshEventAsync(ContextRefreshedEvent event) {
        print ("@EventListener & @Async onRefreshEvent:" + event);
    }


    @EventListener
    public void onClosedEvent(ContextClosedEvent event) {
        print ("@EventListener onClosedEvent:" + event);
    }


    public void print(Object printable) {
        System.out.printf ("thread:%s,event:%s \n", Thread.currentThread ().getName (), printable);
    }

}
