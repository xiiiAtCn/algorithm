package dsa;


import dsa.exception.ExceptionBoundaryViolation;
import dsa.exception.ExceptionListEmpty;
import dsa.exception.ExceptionPositionInvalid;
import dsa.interfaces.List;
import dsa.interfaces.Position;

import java.util.Iterator;

public class List_DLNode implements List {

    protected int numElem;  //列表的实际大小
    protected DLNode header, trailer;

    public List_DLNode() {
        numElem = 0;
        header = new DLNode(null, null, null);
        trailer = new DLNode(null, header, null);
        header.setNext(trailer);
    }

    /**
     *
     * 辅助方法
     */
    //检查给定位置在列表中是否合法
    protected DLNode checkPosition(Position position) throws ExceptionPositionInvalid {
        if (null == position) {
            throw new ExceptionPositionInvalid("意外: 传递给List_DLNode的位置是null");
        }
        if (position == header) {
            throw new ExceptionPositionInvalid("意外: 头节点哨兵位置非法");
        }
        if (position == trailer) {
            throw new ExceptionPositionInvalid("意外: 尾节点哨兵位置非法");
        }
        DLNode temp = (DLNode) position;
        return temp;
    }

    public int getSize() {
        return numElem;
    }

    public boolean isEmpty() {
        return numElem == 0;
    }

    public Position first() throws ExceptionListEmpty {
        if (isEmpty()) {
            throw new ExceptionListEmpty("意外: 列表空");
        }
        return header.getNext();
    }

    public Position last() {
        if (isEmpty()) {
            throw new ExceptionListEmpty("意外: 列表空");
        }
        return trailer.getPrev();
    }

    public Position getNext(Position position) throws ExceptionPositionInvalid, ExceptionBoundaryViolation {
        DLNode v = checkPosition(position);
        DLNode next = v.getNext();
        if (next == trailer) {
            throw new ExceptionBoundaryViolation("意外: 企图越过列表后端");
        }
        return next;
    }

    public Position getPrev(Position position) throws ExceptionPositionInvalid, ExceptionBoundaryViolation {
        DLNode v = checkPosition(position);
        DLNode prev = v.getPrev();
        if (prev == header) {
            throw new ExceptionBoundaryViolation("意外: 企图越过列表前端");
        }
        return prev;
    }

    public Position insertFirst(Object object) {
        numElem++;
        DLNode newNode = new DLNode(object, header, header.getNext());
        header.getNext().setPrev(newNode);
        header.setNext(newNode);
        return newNode;
    }

    public Position insertLast(Object object) {
        numElem++;
        DLNode newNode = new DLNode(object, trailer.getPrev(), trailer);
        trailer.setPrev(newNode);
        trailer.getPrev().setNext(newNode);
        return newNode;
    }

    public Position insertAfter(Position p, Object object) throws ExceptionPositionInvalid {
        DLNode v = checkPosition(p);
        numElem++;
        DLNode newNode = new DLNode(object, v, v.getNext());
        v.setNext(newNode);
        v.getNext().setPrev(newNode);
        return newNode;
    }

    public Position insertBefore(Position position, Object object) throws ExceptionPositionInvalid {
        DLNode v = checkPosition(position);
        numElem++;
        DLNode newNode = new DLNode(object, v.getPrev(), v);
        v.getPrev().setNext(newNode);
        v.setPrev(newNode);
        return newNode;
    }

    public Object remove(Position position) throws ExceptionPositionInvalid {
        DLNode v = checkPosition(position);
        numElem--;
        DLNode vPrev = v.getPrev();
        DLNode vNext = v.getNext();
        vPrev.setNext(vNext);
        vNext.setPrev(vPrev);
        Object vObject = v.getElement();
        //java如何通知内存回收
        //将该位置从列表中摘除，以便系统回收其占用的空间
        v.setPrev(null);
        v.setNext(null);
        return vObject;
    }

    public Object removeFirst() {
        return remove(header.getNext());
    }

    public Object removeLast() {
        return remove(trailer.getPrev());
    }

    public Object replace(Position position, Object object) throws ExceptionPositionInvalid {
        DLNode v = checkPosition(position);
        Object oldElem = v.getElement();
        v.setElement(object);
        return oldElem;
    }

    public Iterator positions() {
        return new IteratorPosition(this);
    }

    public Iterator elements() {
        return new IteratorElement(this);
    }
}
