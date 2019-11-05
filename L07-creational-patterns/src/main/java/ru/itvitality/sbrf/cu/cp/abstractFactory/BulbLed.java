package ru.itvitality.sbrf.cu.cp.abstractFactory;

public class BulbLed implements Bulb {
    @Override
    public void light() {
        System.out.println("Led light");
    }
}
