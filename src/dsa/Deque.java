package dsa;

import dsa.exception.ExceptionQueueEmpty;

public interface Deque {
    int getSize();

    boolean isEmpty();

    Object first() throws ExceptionQueueEmpty;

    Object last() throws ExceptionQueueEmpty;

    void insertFirst(Object object);

    void insertLast(Object object);

    Object removeFirst() throws ExceptionQueueEmpty;

    Object removeLast() throws ExceptionQueueEmpty;

    void traverse();
}
