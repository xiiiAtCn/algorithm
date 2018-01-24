package dsa;

import dsa.interfaces.Comparator;
import dsa.interfaces.PQueue;
import dsa.interfaces.Sequence;
import dsa.interfaces.Sorter;

public class Sorter_PQueue implements Sorter {

    private Comparator comparator;

    public Sorter_PQueue() {
        this(new ComparatorDefault());
    }

    public Sorter_PQueue(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(Sequence sequence) {
        Sequence t = new Sequence_DLNode();
        while (!sequence.isEmpty()) {
            Object e = sequence.removeFirst();
            t.insertLast(new EntryDefault(e, e));
        }

        PQueue pQueue = new PQueue_Heap(comparator, t);
        while (!pQueue.isEmpty()) {
            sequence.insertLast(pQueue.delMin().getValue());
            System.out.println("\t: \t" + sequence.last().getElement());
        }
    }
}
