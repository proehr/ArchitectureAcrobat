package com.pli.codes.architectureacrobat.observer;


public interface Observer {

    //method to update the observer, used by subject
    void update(String paramName, Object oldValue, Object newValue);
}
