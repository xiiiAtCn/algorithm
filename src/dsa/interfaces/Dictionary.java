package dsa.interfaces;

public interface Dictionary {
    int getSize();

    boolean isEmpty();

    Entry find(Object key);

    Iterator findAll(Object key);

    Entry insert(Object key, Object value);

    Entry remove(Object key);

    Iterator entries();
}
