package dsa;

import dsa.interfaces.EqualityTester;

public class EqualityTesterDefault implements EqualityTester {

    public EqualityTesterDefault() {
    }

    @Override
    public boolean isEqualTo(Object a, Object b) {
        return a.equals(b);
    }
}
