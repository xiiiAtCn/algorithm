package dsa;

import dsa.interfaces.Position;

public class Node<T> implements Position<T> {
    private T element;
    private Node next;

    public Node() {
        this(null, null);
    }

    public Node(T e, Node n) {
        element = e;
        next = n;
    }

    public T getElement() {
        return element;
    }

    public T setElement(T element) {
        T oldElement = this.element;
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