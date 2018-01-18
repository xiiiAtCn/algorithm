package dsa;

import dsa.interfaces.Position;

public class DLNode implements Position {

    private Object element;
    private DLNode prev;
    private DLNode next;

    public DLNode() {
        this(null, null, null);
    }

    public DLNode(Object e, DLNode p, DLNode n) {
        element = e;
        prev = p;
        next = n;
    }

    public Object getElement() {
        return element;
    }

    public Object setElement(Object object) {
        Object oldElement = element;
        element = object;
        return oldElement;
    }

    public DLNode getNext() {
        return next;
    }

    public DLNode getPrev() {
        return prev;
    }

    public void setPrev(DLNode prev) {
        this.prev = prev;
    }

    public void setNext(DLNode next) {
        this.next = next;
    }
}
