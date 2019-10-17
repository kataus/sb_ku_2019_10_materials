package ru.sbrf.ku.java.generics.bounds.entries;

public class HomeCat extends Cat {
    private final String name;

    public HomeCat( String name ) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HomeCat, name:" + name;
    }
}
