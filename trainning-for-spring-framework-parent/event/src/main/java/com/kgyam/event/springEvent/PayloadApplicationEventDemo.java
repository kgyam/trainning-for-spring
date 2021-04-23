package com.kgyam.event.springEvent;

import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 在发布事件的时候，如果发布的是一个Object对象，应用上下文会将其包装成PayloadApplicationEvent
 * <p>
 * <p>
 * 所以自己没必要继承实现一个PayloadApplicationEvent的子类，而且继承PayloadApplicationEvent容易出现问题
 * 对于泛型的处理上并没有很好的支持
 * <p>
 * 下面示例会抛出异常：Caused by: java.lang.IllegalArgumentException: Mismatched number of generics specified
 * {@link org.springframework.context.PayloadApplicationEvent}
 *
 * @author kg yam
 * @date 2021-04-21 16:18
 * @since
 */
public class PayloadApplicationEventDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext ();
        applicationContext.register (PayloadApplicationEventDemo.class);

        applicationContext.addApplicationListener (new ApplicationListener<MyPayloadApplicationEvent> () {
            @Override
            public void onApplicationEvent(MyPayloadApplicationEvent event) {
                System.out.println ("MyPayloadApplicationEvent:" + event);
            }
        });

        applicationContext.refresh ();

        applicationContext.close ();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent (new MyPayloadApplicationEvent (this, "hello"));
    }


    static class MyPayloadApplicationEvent extends PayloadApplicationEvent<String> {
        /**
         * Create a new PayloadApplicationEvent.
         *
         * @param source  the object on which the event initially occurred (never {@code null})
         * @param payload the payload object (never {@code null})
         */
        public MyPayloadApplicationEvent(Object source, String payload) {
            super (source, payload);
        }
    }
}
