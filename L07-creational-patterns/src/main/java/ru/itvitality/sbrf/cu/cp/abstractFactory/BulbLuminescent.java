package ru.itvitality.sbrf.cu.cp.abstractFactory;

public class BulbLuminescent implements Bulb {
    @Override
    public void light() {
        System.out.println("Luminescent light");
    }
}
