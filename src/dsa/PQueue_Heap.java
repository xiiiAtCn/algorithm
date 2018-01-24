package dsa;

import dsa.exception.ExceptionKeyInvalid;
import dsa.exception.ExceptionPQueueEmpty;
import dsa.interfaces.Comparator;
import dsa.interfaces.Entry;
import dsa.interfaces.PQueue;
import dsa.interfaces.Sequence;

public class PQueue_Heap implements PQueue {

    private Comparator comparator;
    private Sequence sequence;

    public PQueue_Heap(Comparator comparator, Sequence sequence) {
        this.comparator = comparator;
        this.sequence = sequence;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Entry getMin() throws ExceptionPQueueEmpty {
        return null;
    }

    @Override
    public Entry insert(Object key, Object value) throws ExceptionKeyInvalid {
        return null;
    }

    @Override
    public Entry delMin() throws ExceptionPQueueEmpty {
        return null;
    }
}
