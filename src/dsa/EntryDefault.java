package dsa;

import dsa.interfaces.Entry;

public class EntryDefault implements Entry {

    protected Object key;
    protected Object value;

    public EntryDefault(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
    @Override
    public Object getKey() {
        return key;
    }

    @Override
    public Object setKey(Object k) {
        Object bak = key;
        key = k;
        return bak;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public Object setValue(Object v) {
        Object bak = value;
        value = v;
        return bak;
    }
}
