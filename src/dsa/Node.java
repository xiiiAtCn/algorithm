package dsa;

public class Node implements Position{
    private Object element;
    private Node next;

    public Node() {
        this(null, null);
    }

    public Node(Object e, Node n) {
        element = e;
        next = n;
    }

    public Object getElement() {
        return element;
    }

    public Object setElement(Object element) {
        Object oldElement = this.element;
        this.element = element;
        return oldElement;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node newNext) {
        next = newNext;
    }
}