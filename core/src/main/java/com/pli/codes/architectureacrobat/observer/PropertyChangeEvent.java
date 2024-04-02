package com.pli.codes.architectureacrobat.observer;


import java.util.ArrayList;
import java.util.List;

public class PropertyChangeEvent {

    private final Object target;

    private final List<Observer> observers = new ArrayList<Observer>();

    public PropertyChangeEvent(Object target) {
        this.target = target;
    }

    //methods to register and unregister observers
    public void register(Observer obj) {
        if (obj == null) {
            throw new NullPointerException("Null Observer");
        }
        if (!observers.contains(obj)) {
            observers.add(obj);
        }
    }

    public void unregister(Observer obj) {
        observers.remove(obj);
    }

    public void unregisterAll() {
        observers.clear();
    }

    //method to notify observers of change
    public void notifyObservers(String paramName, Object oldValue, Object newValue) {
        for (Observer obj : observers) {
            obj.update(paramName, oldValue, newValue);
        }
    }


}
