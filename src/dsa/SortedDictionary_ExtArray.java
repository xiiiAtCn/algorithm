package dsa;

import dsa.interfaces.*;

/**
 *   @author lungern xiii.at.cn@gmail.com
 *   @date 2018/2/9
 *   基于有序查找表实现的有序词典
 *
 */

public class SortedDictionary_ExtArray implements SortedDictionary {

    Vector S;
    Comparator C;

    public SortedDictionary_ExtArray(Comparator comparator) {
        S = new Vector_ExtArray();
        C = comparator;
    }

    public SortedDictionary_ExtArray() {
        this(new ComparatorDefault());
    }

    private static int binSearch(Vector s, Comparator c, Object key, int lo, int hi) {
        if (lo > hi) {
            return lo;
        }
        int mi = (lo + hi) >> 1;
        Entry e = (Entry) s.getAtRank(mi);
        int flag = c.compare(key, e.getKey());
        if (flag < 0) {
            return binSearch(s, c, key, lo, mi - 1);
        } else if (flag > 0) {
            return binSearch(s, c, key, mi + 1, hi);
        } else {
            return mi;
        }
    }

    @Override
    public Entry first() {
        return isEmpty()? null: (Entry) S.getAtRank(0);
    }

    @Override
    public Entry last() {
        return isEmpty()? null: (Entry) S.getAtRank(getSize() - 1);
    }

    @Override
    public Iterator successors(Object key) {
        List L = new List_DLNode();
        int k = binSearch(S, C, key, 0, getSize() - 1);
        if (0 > k || k >= getSize() || (0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey()))) {
            return new IteratorElement(L);
        }
        while (0 <= --k) {
            if (0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey())) {
                break;
            }
            L.insertLast(S.getAtRank(k));
        }
        return new IteratorElement(L);
    }

    @Override
    public Iterator predecessors(Object key) {
        List L = new List_DLNode();
        int k = binSearch(S, C, key, 0, getSize() - 1);
        if (0 > k || k >= getSize() || (0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey()))) {
            return new IteratorElement(L);
        }
        while (getSize()> ++k) {
            if (0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey())) {
                break;
            }
        }
        while (0 <= --k) {
            L.insertLast(S.getAtRank(k));
        }
        return new IteratorElement(L);
    }

    @Override
    public int getSize() {
        return S.getSize();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Entry find(Object key) {
        int k = binSearch(S, C, key, 0, getSize() - 1);
        if (0 > k || k >= getSize() || (0 != C.compare(key, ((Entry) (S.getAtRank(k))).getKey()))) {
            return null;
        }
        return (Entry) S.getAtRank(k);
    }

    @Override
    public Iterator findAll(Object key) {
        List L = new List_DLNode();
        int k = binSearch(S, C, key, 0, getSize() - 1);
        if (0 > k || k >= getSize() || (0 != C.compare(key, ((Entry) (S.getAtRank(k))).getKey()))) {
            return new IteratorElement(L);
        }
        L.insertLast(S.getAtRank(k));
        int lo = k;
        while (0 <= --lo) {
            if (0 != C.compare(key, ((Entry) S.getAtRank(lo)).getKey())) {
                break;
            }
            L.insertFirst(S.getAtRank(lo));
        }
        int hi = k;
        while (++hi < getSize()) {
            if (0 != C.compare(key, ((Entry) (S.getAtRank(k))).getKey())) {
                L.insertLast(S.getAtRank(hi));
            }
        }
        return new IteratorElement(L);
    }

    @Override
    public Entry insert(Object key, Object value) {
        Entry e = new EntryDefault(key, value);
        if (S.isEmpty()) {
            return (Entry) S.insertAtRank(0, e);
        }
        return (Entry) S.insertAtRank(binSearch(S, C, key, 0, getSize() - 1), e);
    }

    @Override
    public Entry remove(Object key) {
        int k = binSearch(S, C, key, 0, getSize() - 1);
        if (0 > k || k >= getSize() || (0 != C.compare(key, ((Entry) S.getAtRank(k)).getKey()))) {
            return null;
        }
        return (Entry) S.removeAtRank(k);
    }

    @Override
    public Iterator entries() {
        List L = new List_DLNode();
        for (int i = 0; i < getSize(); i++) {
            L.insertLast(S.getAtRank(i));
        }
        return new IteratorElement(L);
    }
}
