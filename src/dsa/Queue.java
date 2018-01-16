package dsa;

public interface Queue {
    int getSize();

    boolean isEmpty();

    Object front() throws ExceptionQueueEmpty;

    void enqueue(Object object) throws ExceptionQueueFull;

    Object dequeue() throws ExceptionQueueEmpty;

    void traversal();
}
