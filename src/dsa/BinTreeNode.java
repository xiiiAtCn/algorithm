package dsa;

import dsa.interfaces.BinTreePosition;
import dsa.interfaces.Iterator;

public class BinTreeNode<T> implements BinTreePosition<T> {

    protected T element;
    protected BinTreePosition<T> parent;
    protected BinTreePosition<T> lChild;
    protected BinTreePosition<T> rChild;
    protected int size;
    protected int height;
    protected int depth;

    public BinTreeNode() {
        this(null, null, true, null, null);
    }

    public BinTreeNode(
            T element,
            BinTreePosition<T> parent,
            boolean asLChild,
            BinTreePosition<T> lChild,
            BinTreePosition<T> rChild
    ) {
        size = 1;
        height = depth = 0;
        parent = lChild = rChild = null;
        this.element = element;
        if (null != parent) {
            if (asLChild) {
                parent.attachL(this);
            } else {
                parent.attachR(this);
            }
        }
        if (null != lChild) {
            attachL(lChild);
        }
        if (null != rChild) {
            attachR(rChild);
        }
    }

    @Override
    public boolean hasParent() {
        return null == parent;
    }

    @Override
    public BinTreePosition<T> getParent() {
        return parent;
    }

    @Override
    public void setParent(BinTreePosition<T> parent) {
        this.parent = parent;
    }

    @Override
    public boolean isLeaf() {
        return !(isLChild() || isRChild());
    }

    @Override
    public boolean isLChild() {
        return hasParent() && this == getParent().getLChild();
    }

    @Override
    public boolean hasLChild() {
        return lChild != null;
    }

    @Override
    public BinTreePosition<T> getLChild() {
        return lChild;
    }

    @Override
    public void setLChild(BinTreePosition<T> lChild) {
        this.lChild = lChild;
    }

    @Override
    public boolean isRChild() {
        return hasParent() && this == getParent().getRChild();
    }

    @Override
    public boolean hasRChild() {
        return null != rChild;
    }

    @Override
    public BinTreePosition<T> getRChild() {
        return rChild;
    }

    @Override
    public void setRChild(BinTreePosition<T> rChild) {
        this.rChild = rChild;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void updateSize() {
        size = 1;
        if (hasLChild()) {
            size += getLChild().getSize();
        }
        if (hasRChild()) {
            size += getRChild().getSize();
        }
        if (hasParent()) {
            getParent().updateSize();
        }
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void updateHeight() {
        height = 0;
        if (hasLChild()) {
            height = Math.max(height, getLChild().getHeight() + 1);
        }
        if (hasRChild()) {
            height = Math.max(height, getRChild().getHeight() + 1);
        }
        if (hasParent()) {
            getParent().updateHeight();
        }
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public void updateDepth() {
        depth = 0;
        depth = hasParent() ? 1 + getParent().getHeight() : 0;
        if (hasLChild()) {
            getLChild().updateDepth();
        }
        if (hasRChild()) {
            getRChild().updateDepth();
        }
    }

    @Override
    public BinTreePosition<T> getPrev() {
        return null;
    }

    @Override
    public BinTreePosition<T> getSucc() {
        return null;
    }

    @Override
    public BinTreePosition<T> secede() {
        return null;
    }

    @Override
    public BinTreePosition<T> attachL(BinTreePosition<T> c) {
        return null;
    }

    @Override
    public BinTreePosition<T> attachR(BinTreePosition<T> c) {
        return null;
    }

    @Override
    public Iterator<T> elementsPreOrder() {
        return null;
    }

    public Iterator<T> elementsInOrder() {
        return null;
    }

    public Iterator<T> elementsPostOrder() {
        return null;
    }

    public Iterator<T> elementsLevelOrder() {
        return null;
    }

    public T getElement() {
        return element;
    }

    public T setElement(T object) {
        T bak = object;
        element = object;
        return bak;
    }
}
