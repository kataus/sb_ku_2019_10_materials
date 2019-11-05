package ru.itvitality.sbrf.cu.cp.singleton;

public class Demo {
    public static void main(String[] args) {
        System.out.println("begin");
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1.equals(singleton2));
    }
}
