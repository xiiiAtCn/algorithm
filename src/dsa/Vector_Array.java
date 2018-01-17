package dsa;

public class Vector_Array implements Vector {
    private final int N = 1024;
    private int n = 0;

    private Object[] A;

    public Vector_Array() {
        A = new Object[N];
        n = 0;
    }

    public int getSize() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Object getAtRank(int r) throws ExceptionBoundaryViolation {
        if (0 > r || r >= n) {
            throw new ExceptionBoundaryViolation("意外: 秩越界");
        }
        return A[r];
    }

    public Object replaceAtRank(int r, Object object) throws ExceptionBoundaryViolation {
        if (0 > r || r >= n) {
            throw new ExceptionBoundaryViolation("意外: 秩越界");
        }
        Object bak = A[r];
        A[r] = object;
        return bak;
    }

    public Object insertAtRank(int r, Object object) throws ExceptionBoundaryViolation {
        if (0 > r || r > n) {
            throw new ExceptionBoundaryViolation("意外: 秩越界");
        }
        if (r >= N) {
            throw new ExceptionBoundaryViolation("意外: 数组溢出");
        }
        for (int i = n; i > r; i --) {
            A[i] = A[i - 1];
        }
        A[r] = object;
        n++;
        return object;
    }

    public Object removeAtRank(int r) throws ExceptionBoundaryViolation {
        if (0 > r || r >= n) {
            throw new ExceptionBoundaryViolation("意外: 秩越界");
        }
        if (r >= N) {
            throw new ExceptionBoundaryViolation("意外: 数组溢出");
        }
        Object object = A[r];
        for (int i = r; i < n; i++) {
            A[i] = A[i + 1];
        }
        n--;
        return object;
    }

    public static void main(String[] args) {
        Vector_Array array = new Vector_Array();
        array.insertAtRank(0, new Integer(0));
        array.insertAtRank(1, new Integer(1));
        array.insertAtRank(2, new Integer(2));
        array.insertAtRank(3, new Integer(3));

        array.removeAtRank(2);
        System.out.println();
    }
}
