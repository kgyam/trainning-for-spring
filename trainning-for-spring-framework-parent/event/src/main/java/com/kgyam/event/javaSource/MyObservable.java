package com.kgyam.event.javaSource;

import java.util.Observable;

/**
 * @author kg yam
 * @date 2021-04-19 17:19
 * @since
 */
public class MyObservable extends Observable {

    @Override
    public void notifyObservers() {
        super.setChanged ();
        super.notifyObservers ();
        super.clearChanged ();
    }

    @Override
    public void notifyObservers(Object arg) {
        super.setChanged ();
        super.notifyObservers (arg);
        super.setChanged ();
    }
}
