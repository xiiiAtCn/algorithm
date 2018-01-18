package dsa.interfaces;

import dsa.exception.ExceptionBoundaryViolation;

public interface Vector {
    int getSize();

    boolean isEmpty();

    Object getAtRank(int r) throws ExceptionBoundaryViolation;

    Object replaceAtRank(int r, Object object) throws ExceptionBoundaryViolation;

    Object insertAtRank(int r, Object object) throws ExceptionBoundaryViolation;

    Object removeAtRank(int r) throws ExceptionBoundaryViolation;
}
