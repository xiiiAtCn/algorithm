package dsa;


import dsa.exception.ExceptionNoSuchElement;
import dsa.interfaces.Iterator;
import dsa.interfaces.List;
import dsa.interfaces.Position;

public class IteratorElement implements Iterator {
    private List list;
    private Position nextPosition;

    public IteratorElement() {
        list = null;
    }

    public IteratorElement(List list) {
        this.list = list;
        if (list.isEmpty()) {
            nextPosition = null;
        } else {
            nextPosition = list.first();
        }
    }

    public boolean hasNext() {
        return null != nextPosition;
    }

    public Object getNext() throws ExceptionNoSuchElement{
        if (!hasNext()) {
            throw new ExceptionNoSuchElement("意外: 没有下一元素");
        }
        Position currentPosition = nextPosition;
        if (currentPosition != list.last()) {
            nextPosition = list.getNext(currentPosition);
        } else {
            nextPosition = null;
        }
        return currentPosition.getElement();
    }
}
