package ru.itvitality.sbrf.cu.l08.log.cache;


public class DataProcessor {

    static void process(Data d) {
        System.out.println("key:" + d.getId() + " values:" + d.getValues());
        d.getValues().clear();
    }

}
