package ru.itvitality.sbrf.cu.cp.abstractFactory;

public class LampholderLed implements Lampholder {
    @Override
    public void hold() {
        System.out.println("Led hold");
    }
}
