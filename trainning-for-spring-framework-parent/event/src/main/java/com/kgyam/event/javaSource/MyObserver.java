package com.kgyam.event.javaSource;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * @author kg yam
 * @date 2021-04-19 17:23
 * @since
 */
public class MyObserver implements Observer, EventListener {
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof EventObject) {
            EventObject eventObject = (EventObject) arg;
            Object source = eventObject.getSource ();
            System.out.println (source);
        } else {
            System.out.println ("arg instanceof EventObject fail");
        }
    }
}
