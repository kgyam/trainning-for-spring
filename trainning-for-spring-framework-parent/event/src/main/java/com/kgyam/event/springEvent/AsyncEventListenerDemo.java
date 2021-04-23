package com.kgyam.event.springEvent;

import com.kgyam.event.customizedSpringEvent.MyApplicationEvent;
import com.kgyam.event.customizedSpringEvent.MyEventListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.util.ErrorHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 编码方式实现事件异步处理&实现事件错误处理机制ErrorHandler
 *
 * @author kg yam
 * @date 2021-04-23 11:56
 * @since
 */
public class AsyncEventListenerDemo {

    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext ();
        applicationContext.addApplicationListener (new MyEventListener ());
        applicationContext.refresh ();
        /*
        依赖查找ApplicationEventMulticaster,并以编码方式设置异步处理事件
         */
        ApplicationEventMulticaster applicationEventMulticaster = applicationContext.getBean (AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
        if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster =
                    (SimpleApplicationEventMulticaster) applicationEventMulticaster;
            ExecutorService executor = Executors.newSingleThreadExecutor (new CustomizableThreadFactory ("async-customize-thread-"));
            simpleApplicationEventMulticaster.setTaskExecutor (executor);
         /*
         这种情况下，由于线程池没有关闭，Main线程不会被关闭。这里需要监听容器关闭事件执行线程池关闭方法
         监听容器关闭事件，关闭线程池
         */
            simpleApplicationEventMulticaster.addApplicationListener (new ApplicationListener<ContextClosedEvent> () {
                @Override
                public void onApplicationEvent(ContextClosedEvent event) {
                    System.out.println ("触发ContextClosedEvent");
                    if (!executor.isShutdown ()) {
                        System.out.println ("关闭线程池");
                        executor.shutdown ();
                    }
                }
            });


            /**
             *  Spring事件错误处理代码
             *  SimpleApplicationEventMulticaster中设置ErrorHandler
             *  {@link org.springframework.util.ErrorHandler}
             */
            simpleApplicationEventMulticaster.setErrorHandler (new ErrorHandler () {
                @Override
                public void handleError(Throwable t) {
                    System.err.println ("ErrorHandler处理事件错误，" + t);
                }
            });

            simpleApplicationEventMulticaster.addApplicationListener (new ApplicationListener<MyApplicationEvent> () {
                @Override
                public void onApplicationEvent(MyApplicationEvent event) {
                    throw new RuntimeException ("主动抛出异常，测试事件错误处理");
                }
            });

        }
        // 发布MyApplicationEvent事件触发异步事件
        applicationEventMulticaster.multicastEvent (new MyApplicationEvent ("AsyncEventListenerDemo"));
        System.out.println ("关闭Spring应用上下文");
        applicationContext.close ();
    }
}
