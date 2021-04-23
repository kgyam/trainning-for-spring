package com.kgyam.event.customizedSpringEvent;

import org.springframework.context.ApplicationEvent;

/**
 * @author kg yam
 * @date 2021-04-21 16:26
 * @since
 */
public class MyApplicationEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyApplicationEvent(String source) {
        super (source);
    }

    @Override
    public String getSource() {
        return (String) super.getSource ();
    }
}
