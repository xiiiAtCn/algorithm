package dsa.interfaces;

import dsa.exception.ExceptionKeyInvalid;
import dsa.exception.ExceptionPQueueEmpty;

public interface PQueue {

    int getSize();

    boolean isEmpty();

    Entry getMin() throws ExceptionPQueueEmpty;

    Entry insert(Object key, Object value) throws ExceptionKeyInvalid;

    Entry delMin() throws ExceptionPQueueEmpty;
}
