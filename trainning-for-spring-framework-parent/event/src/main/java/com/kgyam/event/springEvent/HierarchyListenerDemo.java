package com.kgyam.event.springEvent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * 层次性的Spring 应用上下文，在处理事件的时候会存在重复执行的情况。
 * 例子：因为在AbstractApplicationContext#refresh->AbstractApplicationContext#finishRefresh
 * ->AbstractApplicationContext#publishEvent中，如果存在parent，就会让parent再次发布一次当前事件。
 *
 * 所以下面current-context发布的refresh事件会被处理两次
 *
 * @author kg yam
 * @date 2021-04-21 15:00
 * @since
 */
public class HierarchyListenerDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext ();
        parentContext.setId ("parent-context");
        parentContext.register (MyEventListener.class);

        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext ();
        currentContext.setId ("current-context");
        currentContext.setParent (parentContext);
        currentContext.register (MyEventListener.class);

        parentContext.refresh ();
        currentContext.refresh ();


        /**
         * 这里需要注意顺序。
         *
         * 如果是先执行parent#close,由于parent的应用上下文已经关闭所以下面current的close事件不会传到parent这里。
         * 后续的current的close事件只会被处理一次，就是自己处理
         *
         * 但是如果先执行current#close,由于parent应用上下文还在运行状态，那么current的close事件就会在parent容器
         * 再次发布一次。这里current的close事件就会被处理两次
         */
        parentContext.close ();
        currentContext.close ();
    }


    static class MyEventListener implements ApplicationListener<ContextRefreshedEvent> {

        /*
        校验事件，防止在父子应用上下文下会重复执行，
        用static修饰是为了在同一个classloader中确保只有一个PROCESSED_EVENT,没有static会根据实例创建多个set,失去了校验事件的功能
         */
        private final static Set<ApplicationEvent> PROCESSED_EVENTS = new LinkedHashSet<> ();

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            if (PROCESSED_EVENTS.add (event)) {
                System.out.printf ("监听 Spring 应用上下文[ID: %s]事件:%s \n", event.getApplicationContext ().getId (), event);
            }
        }
    }

}
