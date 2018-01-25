package dsa;

import dsa.exception.ExceptionKeyInvalid;
import dsa.exception.ExceptionPQueueEmpty;
import dsa.interfaces.*;

/**
 * 基于无序列表实现的优先队列
 */
public class PQueue_UnsortedList implements PQueue {

    private List list;
    private Comparator comparator;

    public PQueue_UnsortedList() {
        this(new ComparatorDefault(), null);
    }

    public PQueue_UnsortedList(Comparator comparator) {
        this(comparator, null);
    }

    public PQueue_UnsortedList(Sequence list) {
        this(new ComparatorDefault(), list);
    }

    public PQueue_UnsortedList(Comparator comparator, Sequence sequence) {
        //此处已经对list初始化
        list = new List_DLNode();
        this.comparator = comparator;
        if (!sequence.isEmpty()) {
            Entry entry = (Entry) sequence.removeFirst();
            insert(entry.getKey(), entry.getValue());
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
        if (isEmpty()) {
            throw new ExceptionPQueueEmpty("意外: 优先队列空");
        }
        Position minPos = list.first();
        Position curPos = list.getNext(minPos);
        while (null != curPos) {
            if (0 < comparator.compare(minPos.getElement(), curPos.getElement())) {
                minPos = curPos;
            }
            curPos = list.getNext(curPos);
        }
        return (Entry) minPos.getElement();
    }

    @Override
    public Entry insert(Object key, Object value) throws ExceptionKeyInvalid {
        Entry entry = new EntryDefault(key, value);
        list.insertLast(entry);
        return entry;
    }

    @Override
    public Entry delMin() throws ExceptionPQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionPQueueEmpty("意外: 优先队列空");
        }
        Position minPos = list.first();
        Iterator iterator = list.positions();
        while (iterator.hasNext()) {
            Position curPos = (Position) iterator.getNext();
            if (0 < comparator.compare(((Entry) minPos.getElement()).getKey(),
                    ((Entry) curPos.getElement()).getKey())) {
                minPos = curPos;
            }
        }
        return (Entry)list.remove(minPos);
    }
}
