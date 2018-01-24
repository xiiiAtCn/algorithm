package dsa;

import dsa.interfaces.*;
//TODO  use generics to replace Object
public class ComplBinTree_Vector extends BinTree_LinkedList implements ComplBinTree {

    private Vector tree;

    public ComplBinTree_Vector() {
        tree = new Vector_ExtArray();
        root = null;
    }

    public ComplBinTree_Vector(Sequence sequence) {
        this();
        if (null != sequence) {
            while (!sequence.isEmpty()) {
                addLast(sequence.removeFirst());
            }
        }
    }

    @Override
    public BinTreePosition getRoot() {
        return tree.isEmpty()? null: posOfNode(0);
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public int getSize() {
        return tree.getSize();
    }

    @Override
    public int getHeight() {
        return isEmpty()? -1 : getRoot().getHeight();
    }

    @Override
    public BinTreePosition addLast(Object object) {
        BinTreePosition node = new ComplBinTreeNode_Rank(tree, object);
        root = (BinTreePosition) tree.getAtRank(0);
        return node;
    }

    @Override
    public Object delLast() {
        if (isEmpty()) {
            return null;
        }
        if (1 == getSize()) {
            root = null;
        }
        return tree.removeAtRank(tree.getSize() - 1);
    }

    @Override
    public BinTreePosition posOfNode(int i) {
        return (BinTreePosition) tree.getAtRank(i);
    }
}
