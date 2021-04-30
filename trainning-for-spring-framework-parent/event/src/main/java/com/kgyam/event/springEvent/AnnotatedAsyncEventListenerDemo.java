package com.kgyam.event.springEvent;

import com.kgyam.event.customizedSpringEvent.MyApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 注解方式异步处理事件
 *
 * @author kg yam
 * @date 2021-04-23 16:04
 * @since
 */
@EnableAsync
public class AnnotatedAsyncEventListenerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (AnnotatedAsyncEventListenerDemo.class);
        applicationContext.refresh ();
        applicationContext.publishEvent (new MyApplicationEvent ("AnnotatedAsyncEventListenerDemo#MyApplicationEvent"));
        applicationContext.close ();
    }


    @Async
    @EventListener
    public void onListenerEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.printf ("事件执行当前线程:%s,事件为:%s\n",Thread.currentThread ().getName (),contextRefreshedEvent);
    }
}
