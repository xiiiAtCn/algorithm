package dsa;

import dsa.interfaces.BinTreePosition;
import dsa.interfaces.Vector;

/**
 * 基于秩实现的完全二叉树节点
 */
public class ComplBinTreeNode_Rank extends BinTreeNode {

    private Vector tree;
    private int rank;
    private Object element;

    public ComplBinTreeNode_Rank(Vector tree, Object object) {
        this.tree = tree;
        element = object;
        rank = this.tree.getSize();
        this.tree.insertAtRank(rank, this);
    }

    @Override
    public Object getElement() {
        return element;
    }

    @Override
    public Object setElement(Object element) {
        Object bak = element;
        this.element = element;
        return bak;
    }

    @Override
    public boolean hasParent() {
        return rank != 0 ;
    }

    @Override
    public BinTreePosition getParent() {
        return hasParent()? (BinTreePosition)tree.getAtRank((rank - 1) / 2): null;
    }

    @Override
    public boolean hasLChild() {
        return (1 + rank * 2) < tree.getSize();
    }

    @Override
    public BinTreePosition getLChild() {
        return hasLChild()? (BinTreePosition) tree.getAtRank(rank* 2 + 1): null;
    }

    @Override
    public boolean hasRChild() {
        return 2 + rank * 2 < tree.getSize();
    }

    @Override
    public BinTreePosition getRChild() {
        return hasRChild() ? (BinTreePosition) tree.getAtRank(rank * 2 + 2) : null;
    }

    @Override
    public int getSize() {
        int size = 1;
        if (hasLChild()) {
            size += getLChild().getSize();
        }
        if (hasRChild()) {
            size += getRChild().getSize();
        }
        return size;
    }

    public int getHeight() {
        int hL = hasLChild() ? getLChild().getHeight() : -1;
        int hR = hasRChild() ? getRChild().getHeight() : -1;
        return 1 + Math.max(hL, hR);
    }

    @Override
    public int getDepth() {
        return hasParent() ? 1 + getParent().getDepth(): 0;
    }
}
