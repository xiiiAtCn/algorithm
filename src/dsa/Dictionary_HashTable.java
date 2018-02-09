package dsa;

import dsa.interfaces.*;

/**
 * 基于散列表实现的(无序)词典结构
 * 采用分离链策略解决冲突
 */
public class Dictionary_HashTable implements Dictionary {

    private Dictionary[] A;
    private int length;
    private final double maxLamda = 0.75;
    private int size;
    private EqualityTester tester;

    public Dictionary_HashTable(int n, EqualityTester tester) {
        this.tester = tester;
        length = p(n);
        A = new Dictionary[length];
        for (int i = 0; i < length; i++) {
            A[i] = new Dictionary_DLNode(tester);
        }
        size = 0;
    }

    public Dictionary_HashTable() {
        this(0, new EqualityTesterDefault());
    }

    private int h(Object key) {
        return key.hashCode() % length;
    }

    private static boolean prime(int length) {
        for (int i = 3; i < Math.sqrt(length); i++) {
            if (length / i * i == length) {
                return false;
            }
        }
        return true;
    }

    private static int p(int n) {
        if (3 > n) {
            n = 3;
        }
        n = n | 1;
        while (!prime(n)) {
            n += 2;
        }
        return n;
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Entry find(Object key) {
        return A[h(key)].find(key);
    }

    @Override
    public Iterator findAll(Object key) {
        return A[h(key)].findAll(key);
    }

    @Override
    public Entry insert(Object key, Object value) {
        Entry entry = A[h(key)].insert(key, value);
        size++;
        if (size > length * maxLamda) {
            rehash();
        }
        return entry;
    }

    @Override
    public Entry remove(Object key) {
        Entry oldEntry = A[h(key)].remove(key);
        return oldEntry;
    }

    @Override
    public Iterator entries() {
        List L = new List_DLNode();
        for (int i = 0; i < length; i++) {
            Iterator iterator = A[i].entries();
            while (iterator.hasNext()) {
                L.insertLast(iterator.getNext());
            }
        }
        return new IteratorElement(L);
    }

    private void rehash() {
        Iterator iterator = this.entries();
        length = p(length << 1);
        A = new Dictionary[length];
        for (int i = 0; i < length; i++) {
            A[i] = new Dictionary_DLNode(tester);
        }
        while (iterator.hasNext()) {
            Entry e = (Entry) iterator.getNext();
            Object k = e.getKey();
            Object v = e.getValue();
            A[h(k)].insert(k, v);
        }
    }
}
