package dsa.interfaces;

import dsa.TreeLinkedList;

public interface Tree<T> {
    T getElem();

    T setElem(T obj);

    TreeLinkedList<T> getParent();

    TreeLinkedList<T> getFirstChild();

    TreeLinkedList<T> getNextSibling();

    int getSize();

    int getHeight();

    int getDepth();

}
