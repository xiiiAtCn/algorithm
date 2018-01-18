package dsa.interfaces;

import dsa.exception.ExceptionStackEmpty;

public interface Stack<T> {
    int getSize();

    boolean isEmpty();

    T top() throws ExceptionStackEmpty;

    void push(T ele);

    T pop() throws ExceptionStackEmpty;
}
