package dsa;

import dsa.exception.ExceptionBoundaryViolation;

public class Vector_ExtArray implements Vector {

    private int N = 8;
    private int n ;
    private Object A[];

    public Vector_ExtArray() {
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
            throw new ExceptionBoundaryViolation("意外: 栈越界");
        }
        Object bak = A[r];
        A[r] = object;
        return bak;
    }

    public Object insertAtRank(int r, Object object) throws ExceptionBoundaryViolation {
        if (0 > r || r > n) {
            throw new ExceptionBoundaryViolation("意外: 栈越界");
        }
        if(N == n) {
            N *= 2;
            Object[] B = new Object[N];
            for (int i = 0; i < n; i++) {
                B[i] = A[i];
                A = B;
            }
        }
        for (int i = n; i > r ; i--) {
            A[i] = A[i - 1];
        }
        A[r] = object;
        n++;
        return object;
    }

    public Object removeAtRank(int r) throws ExceptionBoundaryViolation {
        if (0 > r || r >= n) {
            throw new ExceptionBoundaryViolation("意外: 栈越界");
        }
        Object bak = A[r];
        for (int i = r; i < n - 1; i++) {
            A[i] = A[i+1];
        }
        n--;
        return bak;
    }
}
