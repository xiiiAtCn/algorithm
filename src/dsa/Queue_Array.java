package dsa;

public class Queue_Array implements Queue {

    public static final  int CAPACITY = 1024;
    protected int capacity;
    protected Object[] Q;
    protected int f = 0;    //队首元素的位置
    protected int r = 0;    //队尾元素的位置

    public Queue_Array() {
        this(CAPACITY);
    }

    public Queue_Array(int cap) {
        capacity = cap;
        Q = new Object[capacity];
    }

    public int getSize() {
        return (capacity -f + r) % capacity ;
    }

    public boolean isEmpty() {
        return f == r;
    }

    @Override
    public Object front() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("错误：队列空");
        }
        return Q[f];
    }

    @Override
    public void enqueue(Object object) throws ExceptionQueueFull {
        if (getSize() == capacity - 1) {
            throw new ExceptionQueueFull("错误：队列溢出");
        }
        Q[r] = object;
        r = (r + 1) % capacity;
    }

    @Override
    public Object dequeue() throws ExceptionQueueEmpty {
        Object ele;
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("错误：队列空");
        }
        ele = Q[f];
        Q[f] = null;
        f = (f + 1) % capacity;
        return ele;
    }

    @Override
    public void traversal() {
        for (int i = f; i < r; i++) {
            System.out.print(Q[i] + " ");
        }
        System.out.println();
    }
}
