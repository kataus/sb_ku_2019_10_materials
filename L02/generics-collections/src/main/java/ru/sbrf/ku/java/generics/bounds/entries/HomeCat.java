package ru.sbrf.ku.java.generics.bounds.entries;

/**
 * @author sergey
 * created on 23.11.18.
 */
public class HomeCat extends Cat {
    private final String name;

    public HomeCat(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HomeCat, name:" + name;
    }
}
