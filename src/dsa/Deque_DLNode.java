package dsa;

public class Deque_DLNode implements Deque {

    protected DLNode header;
    protected DLNode trailer;
    protected int size;

    public Deque_DLNode() {
        header = new DLNode();
        trailer = new DLNode();
        header.setNext(trailer);
        trailer.setPrev(header);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object first() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("意外: 双端队列为空");
        }
        return header.getNext().getElement();
    }

    public Object last() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("意外: 双端队列为空");
        }
        return trailer.getPrev().getElement();
    }

    public void insertFirst(Object object) {
        DLNode second = header.getNext();
        DLNode first = new DLNode(object, header, second);
        second.setPrev(first);
        header.setNext(first);
        size++;
    }

    public void insertLast(Object object) {
        DLNode second = trailer.getPrev();
        DLNode first = new DLNode(object, second, trailer);
        second.setNext(first);
        trailer.setPrev(first);
        size++;
    }

    public Object removeFirst() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("意外: 双端队列为空");
        }
        DLNode first = header.getNext();
        DLNode second = first.getNext();
        Object object = first.getElement();
        header.setNext(second);
        second.setPrev(header);
        size--;
        return object;
    }

    public Object removeLast() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("意外: 双端队列为空");
        }
        DLNode first = trailer.getPrev();
        DLNode second = first.getPrev();
        Object object = first.getElement();
        trailer.setPrev(second);
        second.setNext(trailer);
        size--;
        return object;
    }

    public void traverse() {
        DLNode node = header.getNext();
        while (node != trailer) {
            System.out.print(node.getElement() + " ");
            node = node.getNext();
        }
        System.out.println();
    }
}
