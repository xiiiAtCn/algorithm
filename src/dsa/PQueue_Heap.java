package dsa;

import dsa.exception.ExceptionKeyInvalid;
import dsa.exception.ExceptionPQueueEmpty;
import dsa.interfaces.*;

public class PQueue_Heap implements PQueue {

    private Comparator comparator;
    private ComplBinTree tree;

    public PQueue_Heap() {
        this(new ComparatorDefault(), null);
    }

    public PQueue_Heap(Comparator comparator) {
        this(comparator, null);
    }

    public PQueue_Heap(Sequence sequence) {
        this(new ComparatorDefault(), sequence);
    }


    public PQueue_Heap(Comparator comparator, Sequence sequence) {
        this.comparator = comparator;
        tree = new ComplBinTree_Vector(sequence);
        if (!tree.isEmpty()) {
            for (int i = tree.getSize() / 2 - 1; i >= 0; i--) {
                percolateDown(tree.posOfNode(i));
            }
        }
    }

    @Override
    public int getSize() {
        return tree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public Entry getMin() throws ExceptionPQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionPQueueEmpty("意外: 优先队列空");
        }
        return (Entry)tree.getRoot().getElement();
    }

    @Override
    public Entry insert(Object key, Object value) throws ExceptionKeyInvalid {
        checkKey(key);
        Entry entry = new EntryDefault(key, value);
        percolateUp(tree.addLast(entry));
        return entry;
    }

    @Override
    public Entry delMin() throws ExceptionPQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionPQueueEmpty("意外: 优先队列为空");
        }
        Entry min = (Entry) tree.getRoot().getElement();
        if (1 == getSize()) {
            tree.delLast();
        } else {
            tree.getRoot().setElement(((ComplBinTreeNode_Rank) tree.delLast()).getElement());
            percolateDown(tree.getRoot());
        }
        return min;
    }

    protected void checkKey(Object key) throws ExceptionKeyInvalid {
        try {
            comparator.compare(key, key);
        } catch (Exception e) {
            throw new ExceptionKeyInvalid("无法比较关键码");
        }
    }

    protected Object key(BinTreePosition position) {
        return ((Entry) position.getElement()).getKey();
    }

    protected void swapParentChild(BinTreePosition parent, BinTreePosition child) {
        Object bak = parent.getElement();
        parent.setElement(child.getElement());
        child.setElement(bak);
    }

    protected void percolateUp(BinTreePosition v) {
        BinTreePosition root = tree.getRoot();
        while (v != tree.getRoot()) {
            BinTreePosition p = v.getParent();
            if (0 >= comparator.compare(key(p), key(v))) {
                break;
            }
            swapParentChild(p, v);
            v = p;
        }
    }

    protected void percolateDown(BinTreePosition v) {
        while (v.hasLChild()) {
            BinTreePosition smallerChild = v.getLChild();
            if (v.hasRChild() && 0 < comparator.compare(key(v.getLChild()), key(v.getRChild()))) {
                smallerChild = v.getRChild();
            }
            if (0 < comparator.compare(key(smallerChild), key(v))) {
                break;
            }
            swapParentChild(v, smallerChild);
            v = smallerChild;
        }
    }
}
