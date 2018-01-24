package dsa;

import dsa.interfaces.Comparator;

public class ComparatorDefault implements Comparator {

    @Override
    public int compare(Object a, Object b) throws ClassCastException{
        return ((Comparable)a).compareTo(b);
    }
}
