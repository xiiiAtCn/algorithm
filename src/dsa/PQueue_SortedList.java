package dsa;

import dsa.exception.ExceptionKeyInvalid;
import dsa.exception.ExceptionPQueueEmpty;
import dsa.interfaces.*;
import javafx.geometry.Pos;

public class PQueue_SortedList implements PQueue {
    private List list;
    private Comparator comparator;

    public PQueue_SortedList() {
        this(new ComparatorDefault(), null);
    }

    public PQueue_SortedList(Comparator comparator) {
        this(comparator, null);
    }

    public PQueue_SortedList(Sequence sequence) {
        this(new ComparatorDefault(), sequence);
    }

    public PQueue_SortedList(Comparator comparator, Sequence sequence) {
        list = new List_DLNode();
        this.comparator = comparator;
        if (null != sequence) {
            while (!sequence.isEmpty()) {
                Entry e = (Entry) sequence.removeFirst();
                insert(e.getKey(), e.getValue());
            }
        }
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Entry getMin() throws ExceptionPQueueEmpty {
        if (list.isEmpty()) {
            throw new ExceptionPQueueEmpty("意外: 优先队列空");
        }
        return (Entry)list.last();
    }

    @Override
    public Entry insert(Object key, Object value) throws ExceptionKeyInvalid {
        Entry entry = new EntryDefault(key, value);
        if (list.isEmpty() || ( 0 > comparator.compare(((Entry)list.first().getElement()).getKey(), entry.getKey()))) {
            list.insertFirst(entry);
        } else {
            Position curPos = list.last();
            while (0 > comparator.compare(((Entry) curPos.getElement()).getKey(), entry.getKey())) {
                curPos = list.getPrev(curPos);
            }
            list.insertAfter(curPos, entry);
        }
        return entry;
    }

    @Override
    public Entry delMin() throws ExceptionPQueueEmpty {
        if (list.isEmpty()) {
            throw new ExceptionPQueueEmpty("意外: 优先队列空");
        }
        return (Entry)list.remove(list.last());
    }
}
