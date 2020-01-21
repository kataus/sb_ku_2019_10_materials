package com.sbrf.cu.java;

public class CustomThread2 implements Runnable {
    private Index index;
    CustomThread2(Index index){this.index = index;}

    @Override
    public void run() {
        for(byte i=1;i<20;i++){
            while (index.getThreadNumber()!=2){}
            synchronized (index) {
                System.out.print(index.getFuncInterface().forAbs(i));
                index.setThreadNumber((short)1);
            }
        }
    }
}
