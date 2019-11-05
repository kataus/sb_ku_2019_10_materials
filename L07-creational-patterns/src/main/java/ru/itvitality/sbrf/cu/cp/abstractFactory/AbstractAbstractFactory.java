package ru.itvitality.sbrf.cu.cp.abstractFactory;

public class AbstractAbstractFactory {

    public AbstractFactory createFactory(String type) {
        if (type.equals("led")) {
            return new LedAbstractFactory();
        } else if (type.equals("luminescent")) {
            return new LuminescentFactory();
        }
        throw new IllegalArgumentException();
    }

}
