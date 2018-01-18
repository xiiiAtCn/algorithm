package dsa;

import dsa.exception.ExceptionQueueEmpty;
import dsa.exception.ExceptionQueueFull;
import dsa.interfaces.Queue;

public class Queue_List<T> implements Queue<T> {

    protected Node<T> head;
    protected Node<T> tail;
    protected int size;

    public Queue_List() {
        head = tail = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T front() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("意外: 队列空");
        }
        return head.getElement();
    }

    public void enqueue(T object) throws ExceptionQueueFull {
        Node node = new Node();
        node.setElement(object);
        node.setNext(null);
        if(isEmpty()) {
            head = node;
        } else {
            tail = node;
        }
        size++;
    }

    public T dequeue() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("意外: 队列空");
        }
        T obj = head.getElement();
        head = head.getNext();
        size--;
        if (isEmpty()) {
            tail = null;
        }
        return obj;
    }

    public void traversal() {
        Node p = head;
        while (null != p) {
            System.out.print(p.getElement() + " ");
            p = p.getNext();
        }
    }
}
