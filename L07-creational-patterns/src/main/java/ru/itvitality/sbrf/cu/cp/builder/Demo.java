package ru.itvitality.sbrf.cu.cp.builder;

/*
 * 1) Реализовать builder:
 *   а) создать класс BigObjectBuilder в котором будут теже поля, что и в BigObject. Поля заполняется через методы with*("param")
 *   б) создат в классе BigObjectBuilder метод build(), который вызывает конструктор BigObject и инициализирует все поля
 *   в) * перед созданием BigObject проверить, что поля param1 и param2 не null
 *   г) реализовать паттерн с помощью библиотеки Lombok. Сделать Delombok - посмотреть как реализован builder
 * */

public class Demo {
    public static void main(String[] args) {
        BigObject bigObject = new BigObject.BigObjectBuilder("1")
                .withParam2("2")
                .withParam3("3")
                .withParam4("4")
                .withParam5("5")
                .build();
        System.out.println(bigObject);
    }
}
