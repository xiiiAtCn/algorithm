package dsa;

import dsa.exception.ExceptionNoSuchElement;
import dsa.interfaces.Iterator;
import dsa.interfaces.List;
import dsa.interfaces.Position;

public class IteratorTree implements Iterator {

    private List list;
    private Position nextPosition;

    public IteratorTree() {
        list = null;
    }

    public <T> void elementsPreOrderIterator(TreeLinkedList<T> list) {
        if (null == list) {
            return;
        }
        this.list.insertLast(list);
        TreeLinkedList<T> subTree = list.getFirstChild();
        while (null != subTree) {
            this.elementsPreOrderIterator(subTree);
            subTree = subTree.getNextSibling();
        }
    }

    public <T> void elementsPostOrderIterator(TreeLinkedList<T> list) {
        if (null == list) {
            return;
        }
        TreeLinkedList<T> subTree = list.getFirstChild();
        while (null != subTree) {
            this.elementsPostOrderIterator(subTree);
            subTree = subTree.getNextSibling();
        }
        this.list.insertLast(list);
    }

    public <T> void levelTraversalIterator(TreeLinkedList<T> list) {
        if (null == list) {
            return;
        }
        Queue_List<TreeLinkedList> queueList = new Queue_List<>();
        queueList.enqueue(list);
        while (!queueList.isEmpty()) {
            TreeLinkedList<T> treeLinkedList = queueList.dequeue();
            this.list.insertLast(treeLinkedList);
            TreeLinkedList<T> subTree = treeLinkedList.getFirstChild();
            while (null != subTree) {
                queueList.enqueue(subTree);
                subTree = subTree.getNextSibling();
            }
        }
    }

    public boolean hasNext() {
        return null != nextPosition;
    }

    public Object getNext() throws ExceptionNoSuchElement {
        if (!hasNext()) {
            throw new ExceptionNoSuchElement("意外: 没有下一位置");
        }
        Position currentPosition = nextPosition;
        if (currentPosition == list.last()) {
            nextPosition = null;
        } else {
            nextPosition = list.getNext(currentPosition);
        }
        return currentPosition.getElement();
    }
}
