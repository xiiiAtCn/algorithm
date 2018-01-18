package dsa;


import dsa.exception.ExceptionNoSuchElement;
import dsa.interfaces.Iterator;
import dsa.interfaces.List;
import dsa.interfaces.Position;

public class IteratorPosition implements Iterator {
    private List list;
    private Position nextPosition;

    public IteratorPosition() {
        list = null;
    }

    public IteratorPosition(List list) {
        this.list = list;
        if (list.isEmpty()) {
            nextPosition = null;
        } else {
            nextPosition = list.first();
        }
    }

    public boolean hasNext() {
        return nextPosition != null;
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
        return currentPosition;
    }
}
