package dsa;

import dsa.exception.ExceptionBoundaryViolation;
import dsa.exception.ExceptionPositionInvalid;
import dsa.interfaces.Position;
import dsa.interfaces.Sequence;

public class Sequence_DLNode extends List_DLNode implements Sequence {

    protected void checkRank(int r, int n) throws ExceptionBoundaryViolation{
        if (r < 0 || r >= n) {
            throw new ExceptionBoundaryViolation("意外: 非法的秩" + r + ", 应该属于[0, " + n + ")");
        }
    }

    public Position rank2Pos(int r) throws ExceptionBoundaryViolation {
        DLNode node;
        checkRank(r, getSize());
        if (r < getSize() / 2) {
            node = header.getNext();
            for (int i = 0; i < r; i++) {
                node = node.getNext();
            }
        } else {
            node = trailer.getPrev();
            for (int i = getSize() - 1; i >= r ; i++) {
                node = node.getPrev();
            }
        }
        return node;
    }

    public int pos2Rank(Position position) throws ExceptionPositionInvalid {
        DLNode node = header.getNext();
        int r = 0;
        while (node != trailer) {
            if (node == position) {
                return r;
            }
            node = node.getNext();
            r++;
        }
        throw new ExceptionPositionInvalid("意外: 作为参数的位置不属于序列");
    }

    public Object getAtRank(int r) throws ExceptionBoundaryViolation {
        checkRank(r, getSize());
        return rank2Pos(r).getElement();
    }

    public Object replaceAtRank(int r, Object object) throws ExceptionBoundaryViolation {
        checkRank(r, getSize());
        return replace(rank2Pos(r), object);
    }

    public Object insertAtRank(int r, Object object) throws ExceptionBoundaryViolation {
        checkRank(r, getSize() + 1);
        if (getSize() == r) insertLast(object);
        else insertBefore(rank2Pos(r), object);
        return object;
    }

    public Object removeAtRank(int r) throws ExceptionBoundaryViolation {
        checkRank(r, getSize());
        return remove(rank2Pos(r));
    }
}
