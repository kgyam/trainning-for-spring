package com.kgyam.event.springEvent;

import com.kgyam.event.customizedSpringEvent.MyApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 注解方式异步处理事件
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


    @EventListener
    public void onListenerEvent(MyApplicationEvent myApplicationEvent) {
        System.out.println ("AnnotatedAsyncEventListenerDemo#onListenerEvent");
        System.out.println (myApplicationEvent);
    }
}
