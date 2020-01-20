package com.sbrf.cu.java;

public class CustomThread1 implements Runnable{
    private Index index;
    CustomThread1(Index index){
        this.index=index;
    }

    @Override
    public void run() {
        while (index.getIndex()<=10) {
            while (index.getThreadNumber() != 1) {}
            synchronized (index) {
                if(index.getIndex()<=10) {
                    System.out.print(index.getIndex() + " ");
                    index.setThreadNumber((short) 2);
                }
            }
        }
        index.deccendIndex((short) 2);
        while (index.getIndex()>=1) {
            while (index.getThreadNumber() != 1) {}
            synchronized (index) {
                if(index.getIndex()>=10) {
                    System.out.print(index.getIndex() + " ");
                    index.setThreadNumber((short) 2);
                }
            }
        }
    }
}
