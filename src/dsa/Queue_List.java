package dsa;

public class Queue_List implements Queue {

    protected Node head;
    protected Node tail;
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

    public Object front() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("意外: 队列空");
        }
        return head.getElement();
    }

    public void enqueue(Object object) throws ExceptionQueueFull {
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

    public Object dequeue() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("意外: 队列空");
        }
        Object obj = head.getElement();
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
