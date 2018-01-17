package dsa;

public class Stack_List implements Stack {

    protected Node top;
    protected int size;

    public Stack_List() {
        top = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public Object top() throws ExceptionStackEmpty {
        if (isEmpty()) {
            throw new ExceptionStackEmpty("意外: 栈空");
        }
        return top.getElement();
    }

    @Override
    public void push(Object ele) {
        Node v = new Node(ele, top);
        top = v;
        size++;
    }

    @Override
    public Object pop() throws ExceptionStackEmpty {
        if (isEmpty()) {
            throw new ExceptionStackEmpty("意外: 栈空");
        }
        Object temp = top.getElement();
        top = top.getNext();
        size--;
        return temp;
    }
}
