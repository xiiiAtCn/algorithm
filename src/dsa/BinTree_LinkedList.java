package dsa;

import dsa.interfaces.BinTree;
import dsa.interfaces.BinTreePosition;
import dsa.interfaces.Iterator;
//TODO add generics type in class declaration
public class BinTree_LinkedList implements BinTree {
    protected BinTreePosition root;

    public BinTree_LinkedList() {
        this(null);
    }

    public BinTree_LinkedList(BinTreePosition position) {
        root = position;
    }

    @Override
    public BinTreePosition getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return null == root;
    }

    public int getSize() {
        return isEmpty() ? 0 : root.getSize();
    }

    @Override
    public int getHeight() {
        return isEmpty() ? -1 : root.getHeight();
    }

    @Override
    public Iterator elementsPreOrder() {
        return root.elementsPreOrder();
    }

    @Override
    public Iterator elementsInOrder() {
        return root.elementsInOrder();
    }

    @Override
    public Iterator elementsPostOrder() {
        return root.elementsPreOrder();
    }

    @Override
    public Iterator elementsLevelOrder() {
        return root.elementsLevelOrder();
    }
}
