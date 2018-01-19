package dsa.interfaces;

import dsa.exception.ExceptionBoundaryViolation;
import dsa.exception.ExceptionPositionInvalid;


public interface List<T> {
    int getSize();

    boolean isEmpty();

    Position<T> first();

    Position<T> last();

    Position<T> getNext(Position<T> position) throws ExceptionPositionInvalid, ExceptionBoundaryViolation;

    Position<T> getPrev(Position<T> position) throws ExceptionPositionInvalid, ExceptionBoundaryViolation;

    Position<T> insertFirst(T object);

    Position<T> insertLast(T object);

    Position<T> insertAfter(Position<T> p, T object) throws ExceptionPositionInvalid;

    Position<T> insertBefore(Position<T> position, T object) throws ExceptionPositionInvalid;

    T remove(Position<T> position) throws ExceptionPositionInvalid;

    T removeFirst();

    T removeLast();

    T replace(Position position, T object) throws ExceptionPositionInvalid;

    Iterator<T> positions();

    public Iterator<T> elements();

}
