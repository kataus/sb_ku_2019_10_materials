package ru.sbrf.ku.java.generics;

import java.util.List;

/**
 * @author sergey
 * created on 23.11.18.
 */
public class GenericsMethod {

    private <K,V> void print(K key, V val) {
        System.out.println("key:" + key +", val:" + val);
    }


    public static void main(String[] args) {
        GenericsMethod genericsMethod = new GenericsMethod();
        genericsMethod.print(1, "value");
        genericsMethod.print(2, "value2");
    }

}
