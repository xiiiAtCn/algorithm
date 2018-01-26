package dsa;

import dsa.interfaces.*;

public class Map_DLNode implements Map {

    private List list;
    private EqualityTester tester;

    public Map_DLNode() {
        this(new EqualityTesterDefault());
    }

    public Map_DLNode(EqualityTester tester) {
        list = new List_DLNode();
        this.tester = tester;
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Object get(Object key) {
        Iterator p = list.positions();
        while (p.hasNext()) {
            Position pos = (Position) p.getNext();
            Entry entry = (EntryDefault) pos.getElement();
            if (tester.isEqualTo(entry.getKey(), key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        Iterator p = list.positions();
        while (p.hasNext()) {
            Position pos = (Position) p.getNext();
            Entry entry = (EntryDefault) pos.getElement();
            if (tester.isEqualTo(entry.getKey(), key)) {
                Object oldValue = entry.getValue();
                list.replace(pos, new EntryDefault(key, value));
                return oldValue;
            }
        }
        list.insertFirst(new EntryDefault(key, value));
        return null;
    }

    @Override
    public Object remove(Object key) {
        Iterator p = list.positions();
        while (p.hasNext()) {
            Position pos = (Position) p.getNext();
            Entry entry = (EntryDefault) pos.getElement();
            if (tester.isEqualTo(entry.getKey(), key)) {
                Object oldValue = entry.getValue();
                list.remove(pos);
                return oldValue;
            }
        }
        return null;
    }

    @Override
    public Iterator entries() {
        return new IteratorElement(list);
    }
}
