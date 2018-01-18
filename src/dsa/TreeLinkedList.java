package dsa;

import dsa.interfaces.Tree;

public class TreeLinkedList<T> implements Tree<T> {

    private T element;
    private TreeLinkedList<T> parent, firstChild, nextSibling;

    public TreeLinkedList() {
        this(null, null, null, null);
    }

    public TreeLinkedList(
            T element,
            TreeLinkedList<T> parent,
            TreeLinkedList<T> firstChild,
            TreeLinkedList<T> nextSibling) {
        this.element = element;
        this.parent = parent;
        this.firstChild = firstChild;
        this.nextSibling = nextSibling;
    }

    public T getElem() {
        return element;
    }

    @Override
    public T setElem(T obj) {
        T bak = obj;
        element = obj;
        return bak;
    }

    @Override
    public TreeLinkedList<T> getParent() {
        return parent;
    }

    @Override
    public TreeLinkedList<T> getFirstChild() {
        return firstChild;
    }

    @Override
    public TreeLinkedList<T> getNextSibling() {
        return nextSibling;
    }

    @Override
    public int getSize() {
        int size = 1;
        TreeLinkedList<T> subTree = firstChild;
        while (null != subTree) {
            size += subTree.getSize();
            subTree = subTree.getNextSibling();
        }
        return size;
    }

    public int getHeight() {
        int height = -1;
        TreeLinkedList<T> subTree = firstChild;
        while (subTree != null) {
            height = Math.max(height, subTree.getHeight());
            subTree = subTree.getNextSibling();
        }
        return height + 1;
    }

    public int getDepth() {
        int depth = 0;
        TreeLinkedList<T> p = parent;
        while (null != p) {
            depth++;
            p = p.getParent();
        }
        return depth;
    }
}
