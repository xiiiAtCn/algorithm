package dsa.interfaces;

import dsa.exception.ExceptionQueueEmpty;
import dsa.exception.ExceptionQueueFull;

public interface Queue<T> {
    int getSize();

    boolean isEmpty();

    T front() throws ExceptionQueueEmpty;

    void enqueue(T object) throws ExceptionQueueFull;

    T dequeue() throws ExceptionQueueEmpty;

    void traversal();
}
