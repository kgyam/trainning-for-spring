package com.kgyam.event.javaSource;

/**
 * @author kg yam
 * @date 2021-04-19 17:27
 * @since
 */
public class Demo {
    public static void main(String[] args) {
        MyObservable observable = new MyObservable ();
        observable.addObserver (new MyObserver ());
        observable.notifyObservers (new MyEventObject ("hello world"));
    }
}
