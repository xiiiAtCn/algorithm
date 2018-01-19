package dsa;

import dsa.interfaces.*;

//迭代器该如何定义,才能保证泛型参数的正确性
public class BinTreeNode<T> implements BinTreePosition<T> {

    protected T element;
    protected BinTreePosition<T> parent;
    protected BinTreePosition<T> lChild;
    protected BinTreePosition<T> rChild;
    protected int size;     //后代数目
    protected int height;   //高度
    protected int depth;    //深度

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

    //中序遍历的前驱节点
    @Override
    public BinTreePosition<T> getPrev() {
        if (hasLChild()) {
            return findMaxDescendant(getLChild());
        }
        if (hasRChild()) {
            return getParent();
        }
        BinTreePosition<T> v = this;
        while (v.isLChild()) {
            v = v.getParent();
        }
        return v.getParent();
    }

    //中序遍历的后继节点
    @Override
    public BinTreePosition<T> getSucc() {
        if (hasRChild()) {
            return findMaxDescendant(getRChild());
        }
        if (hasLChild()) {
            return getParent();
        }
        BinTreePosition<T> v = this;
        while (v.isRChild()) {
            v = v.getParent();
        }
        return v.getParent();
    }

    @Override
    public BinTreePosition<T> secede() {
        if (null != parent) {
            if (isLChild()) {
                parent.setLChild(null);
            } else {
                parent.setRChild(null);
            }
            parent.updateSize();
            parent.updateHeight();
            parent = null;
            updateDepth();
        }
        return this;
    }

    @Override
    public BinTreePosition<T> attachL(BinTreePosition<T> c) {
        if (hasLChild()) {
            getLChild().secede();
        }
        if (null != c) {
            c.secede();
            lChild = c;
            c.setParent(this);
            updateSize();
            updateHeight();
            c.updateDepth();
        }
        return this;
    }

    @Override
    public BinTreePosition<T> attachR(BinTreePosition<T> c) {
        if (hasRChild()) {
            getRChild().secede();
        }
        if (null != c) {
            c.secede();
            rChild = c;
            c.setParent(this);
            updateSize();
            updateHeight();
            c.updateHeight();
        }
        return this;
    }

    @Override
    public Iterator<T> elementsPreOrder() {
        List list = new List_DLNode();
        preOrder(list, this);
        return list.elements();
    }

    public Iterator<T> elementsInOrder() {
        List list = new List_DLNode();
        inOrder(list, this);
        return list.elements();
    }

    public Iterator<T> elementsPostOrder() {
        List list = new List_DLNode();
        postOrder(list, this);
        return list.elements();
    }

    public Iterator<T> elementsLevelOrder() {
        List list = new List_DLNode();
        levelOrder(list, this);
        return list.elements();
    }

    public T getElement() {
        return element;
    }

    public T setElement(T object) {
        T bak = object;
        element = object;
        return bak;
    }

    protected static <T> BinTreePosition<T> findMinDescendant(BinTreePosition<T> v) {
        if (null != v) {
            while (v.hasLChild()) {
                v = v.getLChild();
            }
        }
        return v;
    }

    protected static <T> BinTreePosition<T> findMaxDescendant(BinTreePosition<T> v) {
        if (null != v) {
            while (v.hasRChild()) {
                v = v.getRChild();
            }
        }
        return v;
    }

    protected static <T> void preOrder(List list, BinTreePosition<T> v) {
        if (null == v) {
            return;
        }
        list.insertLast(v);
        preOrder(list, v.getLChild());
        preOrder(list, v.getRChild());
    }

    protected static <T> void inOrder(List<BinTreePosition> list, BinTreePosition<T> v) {
        if (null == v) {
            return;
        }
        inOrder(list, v.getLChild());
        list.insertLast(v);
        inOrder(list, v.getRChild());
    }

    protected static <T> void postOrder(List<BinTreePosition> list, BinTreePosition<T> v) {
        if (null == v) {
            return;
        }
        postOrder(list, v.getLChild());
        postOrder(list, v.getRChild());
        list.insertLast(v);
    }

    protected static <T> void levelOrder(List<BinTreePosition> list, BinTreePosition<T> v) {
        Queue_List<BinTreePosition<T>> queue_list = new Queue_List<>();
        queue_list.enqueue(v);
        while (!queue_list.isEmpty()) {
            BinTreePosition<T> position = queue_list.dequeue();
            list.insertLast(position);
            if (v.hasLChild()) {
                queue_list.enqueue(position.getLChild());
            }
            if (v.hasRChild()) {
                queue_list.enqueue(position.getRChild());
            }
        }
    }
}
