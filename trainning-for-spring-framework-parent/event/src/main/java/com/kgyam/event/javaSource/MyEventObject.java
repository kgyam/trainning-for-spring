package com.kgyam.event.javaSource;

import java.util.EventListener;
import java.util.EventObject;

/**
 * @author kg yam
 * @date 2021-04-19 17:25
 * @since
 */
public class MyEventObject extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MyEventObject(Object source) {
        super (source);
    }
}
