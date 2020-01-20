package com.sbrf.cu.java;

/**
 * homework
 *
 */

public class HomeWork {

    public static void main(String[] args) throws InterruptedException {
        Index i = new Index();
        Thread customThread1 = new Thread(new CustomThread1(i));
        Thread customThread2 = new Thread(new CustomThread2(i));
        customThread1.start();
        customThread2.start();
    }

}