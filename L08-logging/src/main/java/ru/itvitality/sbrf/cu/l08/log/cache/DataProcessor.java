package ru.itvitality.sbrf.cu.l08.log.cache;

/**
 * @author sergey
 * created on 14.08.18.
 */
public class DataProcessor {

    static void process(Data d) {
        System.out.println("key:" + d.getId() + " values:" + d.getValues());
        d.getValues().clear();
    }

}
