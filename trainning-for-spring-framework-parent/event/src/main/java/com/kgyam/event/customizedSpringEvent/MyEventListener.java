package com.kgyam.event.customizedSpringEvent;

import org.springframework.context.ApplicationListener;

/**
 * @author kg yam
 * @date 2021-04-21 16:29
 * @since
 */
public class MyEventListener implements ApplicationListener<MyApplicationEvent> {
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.printf ("当前执行线程:[%s],执行事件:[%s]\n", Thread.currentThread ().getName (), event);
    }
}
