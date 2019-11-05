package ru.itvitality.sbrf.cu.cp.objectPool;

public class ConnectionFactory {
    public Connection create() {
        return new ConnectionOracle();
    }
}
