package ru.itvitality.sbrf.cu.cp.abstractFactory;

public class LampholderLuminescent implements Lampholder {
    @Override
    public void hold() {
        System.out.println("Luminescent hold");
    }
}
