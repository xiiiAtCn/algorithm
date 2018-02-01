package dsa;

import dsa.interfaces.*;

/**
 * 基于散列表实现的映射结构
 * 采用分离链策略解决冲突
 */
public class Map_HashTable implements Map {

    private Map [] barrel;                  //桶数组,每个桶本身也是一个映射结构
    private int length;                     //散列表长度
    private final double maxLemda = 0.75;   //装填因子上限
    private int size;                       //映射结构的规模
    private EqualityTester tester;          //判等器

    public Map_HashTable() {
        this(0, new EqualityTesterDefault());
    }

    public Map_HashTable(int length, EqualityTester tester) {
        this.tester = tester;
        this.length = p(length);
        barrel = new Map[this.length];
        for (int i = 0; i < this.length; i++) {
            barrel[i] = new Map_DLNode(this.tester);
        }
        size = 0;
    }

    // 散列定址函数(采用模余法)
    private int h(Object key) {
        return key.hashCode() % length;
    }

    //判断n是否是质数
    private static boolean prime(int n) {
        for (int i = 3; i < Math.sqrt(n); i++) {
            if (n / i * i == n) {
                return false;
            }
        }
        return true;
    }

    //取不小于n的最小质数
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
        return 0 == size;
    }

    @Override
    //若M中存在以key为关键码的条目,则返回该条目的数据对象;否则,返回null
    public Object get(Object key) {
        return barrel[h(key)].get(key);
    }

    @Override
    public Object put(Object key, Object value) {
        Object oldValue = barrel[h(key)].put(key, value);
        if (null == oldValue) {
            size++;
            if (size > length * maxLemda) {
                rehash();
            }
        }
        return oldValue;
    }

    @Override
    public Object remove(Object key) {
        Object oldValue = barrel[h(key)].remove(key);
        if (null != oldValue) {
            size--;
        }
        return oldValue;
    }

    @Override
    public Iterator entries() {
        List list = new List_DLNode();
        for (int i = 0; i < length; i++) {
            Iterator iterator = barrel[i].entries();
            while (iterator.hasNext()) {
                list.insertLast(iterator.getNext());
            }
        }
        return new IteratorElement(list);
    }

    private void rehash() {
        Iterator iterator = this.entries();
        length = p(length << 1);
        barrel = new Map[length];
        for (int i = 0; i < length; i++) {
            barrel[i] = new Map_DLNode(tester);
        }
        while (iterator.hasNext()) {
            Entry e = (Entry) iterator.getNext();
            Object key = e.getKey();
            Object value = e.getValue();
            barrel[h(key)].put(key, value);
        }
    }
}
