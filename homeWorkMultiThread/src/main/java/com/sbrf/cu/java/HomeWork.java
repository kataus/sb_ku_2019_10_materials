package com.sbrf.cu.java;

/**
 * homework
 *
 */

public class HomeWork {

    public static void main(String[] args) throws InterruptedException {
        Index i = new Index();
        i.setFuncInterface(var ->{return (byte)(10-Math.abs(var-10));});
        Thread customThread1 = new Thread(new CustomThread1(i, (short)1, (short)2));
        Thread customThread2 = new Thread(new CustomThread1(i, (short)2, (short)1));
        customThread1.start();
        customThread2.start();
    }

}