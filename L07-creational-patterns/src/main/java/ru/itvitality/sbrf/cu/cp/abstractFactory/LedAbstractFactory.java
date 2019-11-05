package ru.itvitality.sbrf.cu.cp.abstractFactory;

public class LedAbstractFactory implements AbstractFactory {
    @Override
    public Bulb createBulb() {
        return new BulbLed();
    }

    @Override
    public Lampholder createLampholder() {
        return new LampholderLed();
    }
}
