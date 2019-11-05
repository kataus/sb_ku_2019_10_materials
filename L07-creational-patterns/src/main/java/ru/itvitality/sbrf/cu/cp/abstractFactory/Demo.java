package ru.itvitality.sbrf.cu.cp.abstractFactory;


/*
 * 1) Реализовать паттерн фабричный метод для LampholderLed и BulbLed в классе LedFactory
 * 2) Реализовать абстрактуную фабрику по отношение к LuminescentFactory и LedFactory
 *
 * */

public class Demo {

    public Demo(AbstractFactory abstractFactory) {
        Bulb bulb = abstractFactory.createBulb();
        Lampholder lampholder = abstractFactory.createLampholder();

        bulb.light();
        lampholder.hold();
    }

    public static void main(String[] args) {
        AbstractAbstractFactory abstractAbstractFactory = new AbstractAbstractFactory();
        new Demo(abstractAbstractFactory.createFactory("led"));
        new Demo(abstractAbstractFactory.createFactory("luminescent"));
    }

}
