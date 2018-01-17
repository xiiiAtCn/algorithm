package dsa;

import dsa.exception.ExceptionBoundaryViolation;
import dsa.exception.ExceptionPositionInvalid;

import java.util.Iterator;

public interface List {
    int getSize();

    boolean isEmpty();

    Position first();

    Position last();

    Position getNext(Position position) throws ExceptionPositionInvalid, ExceptionBoundaryViolation;

    Position getPrev(Position position) throws ExceptionPositionInvalid, ExceptionBoundaryViolation;

    Position insertFirst(Object object);

    Position insertLast(Object object);

    Position insertAfter(Position p, Object object) throws ExceptionPositionInvalid;

    Position insertBefore(Position position, Object object) throws ExceptionPositionInvalid;

    Object remove(Position position) throws ExceptionPositionInvalid;

    Object removeFirst();

    Object removeLast();

    Object replace(Position position, Object object) throws ExceptionPositionInvalid;

    Iterator positions();

    public Iterator elements();

}
