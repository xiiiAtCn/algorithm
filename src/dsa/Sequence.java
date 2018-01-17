package dsa;

import dsa.exception.ExceptionBoundaryViolation;
import dsa.exception.ExceptionPositionInvalid;

public interface Sequence extends List, Vector {
    Position rank2Pos(int r) throws ExceptionBoundaryViolation;

    int pos2Rank(Position position) throws ExceptionPositionInvalid;
}
