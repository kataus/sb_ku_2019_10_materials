package ru.itvitality.sbrf.cu.cp.singleton;

public class Singleton {
    private Singleton() {
        System.out.println("lazy creation");
    }

    private static class SingletonHolder {
        public static final Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }
}
