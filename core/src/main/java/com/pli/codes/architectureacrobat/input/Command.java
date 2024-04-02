package com.pli.codes.architectureacrobat.input;

public interface Command<T> {

    void execute(T t);

}
