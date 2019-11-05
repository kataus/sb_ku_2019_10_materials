package ru.itvitality.sbrf.cu.cp.objectPool;

public class ConnectionOracle implements Connection {
    @Override
    public void connect() {
        System.out.println("connection to Oracle");
    }

    @Override
    public void select() {
        System.out.println("select from Oracle");
    }
}
