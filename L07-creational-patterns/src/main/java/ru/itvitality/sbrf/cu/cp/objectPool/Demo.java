package ru.itvitality.sbrf.cu.cp.objectPool;

public class Demo {
    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool(5, new ConnectionFactory());
        pool.get().select();
        pool.get().select();
        pool.get().select();
        pool.get().select();
        pool.get().select();
        pool.get().select();
    }
}
