package dsa;

import dsa.interfaces.*;

public class Dictionary_DLNode implements Dictionary {

    private List list;
    private EqualityTester tester;

    public Dictionary_DLNode() {
        this(new EqualityTesterDefault());
    }

    public Dictionary_DLNode(EqualityTester tester) {
        this.tester = tester;
        list = new List_DLNode();
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
    public Entry find(Object key) {
        Iterator position = list.positions();
        while (position.hasNext()) {
            Position pos = (Position) position.getNext();
            Entry entry = (EntryDefault) pos.getElement();
            if (tester.isEqualTo(entry.getKey(), key)) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public Iterator findAll(Object key) {
        List list = new List_DLNode();
        Iterator iterator = list.positions();
        while (iterator.hasNext()) {
            Position position = (Position) iterator.getNext();
            Entry entry = (Entry) position.getElement();
            if (tester.isEqualTo(entry.getKey(), key)) {
                list.insertLast(entry);
            }
        }
        return new IteratorElement(list);
    }

    @Override
    public Entry insert(Object key, Object value) {
        Entry entry = new EntryDefault(key, value);
        list.insertFirst(entry);
        return entry;
    }

    @Override
    public Entry remove(Object key) {
        Iterator iterator = list.positions();
        while (iterator.hasNext()) {
            Position position = (Position) iterator.getNext();
            Entry entry = (Entry) position.getElement();
            if (tester.isEqualTo(entry.getKey(), key)) {
                Entry oldEntry = entry;
                list.remove(position);
                return oldEntry;
            }
        }
        return null;
    }

    @Override
    public Iterator entries() {
        return new IteratorElement(list);
    }
}
