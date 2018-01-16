package dsa;

public interface Stack<T> {
    int getSize();

    boolean isEmpty();

    T top() throws ExceptionStackEmpty;

    void push(T ele);

    T pop() throws ExceptionStackEmpty;
}
