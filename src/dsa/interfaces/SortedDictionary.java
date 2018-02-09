package dsa.interfaces;

public interface SortedDictionary extends Dictionary {

    Entry first();

    Entry last();

    Iterator successors(Object key);

    Iterator predecessors(Object key);
}
