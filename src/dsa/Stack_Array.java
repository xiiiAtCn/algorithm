package dsa;

import dsa.exception.ExceptionStackEmpty;
import dsa.exception.ExceptionStackFull;
import dsa.interfaces.Stack;

public class Stack_Array implements Stack {
    public static final int CAPACITY = 1024;
    protected int capacity;
    protected Object[] S;
    protected int top = -1;

    public Stack_Array(int cap) {
        capacity = cap;
        S = new Object[capacity];
    }

    public int getSize() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public Object top() throws ExceptionStackEmpty {
        if (isEmpty()) {
            throw new ExceptionStackEmpty("意外：栈空");
        }
        return S[top];
    }

    public void push(Object ele) throws ExceptionStackFull {
        if (getSize() == capacity) {
            throw new ExceptionStackFull("意外：栈溢出");
        }
        S[++top] = ele;
    }

    @Override
    public Object pop() throws ExceptionStackEmpty {
        Object ele;
        if (isEmpty()) {
            throw new ExceptionStackEmpty("意外：栈空");
        }
        ele = S[top];
        S[top--] = null;
        return ele;
    }
}
