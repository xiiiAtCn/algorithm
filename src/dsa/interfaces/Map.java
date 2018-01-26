package dsa.interfaces;

public interface Map {

    int getSize();

    boolean isEmpty();

    Object get(Object key);

    Object put(Object key, Object value);

    Object remove(Object key);

    Iterator entries();
}
