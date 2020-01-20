package com.sbrf.cu.java;

public class CustomThread2 implements Runnable {
    private Index index;
    CustomThread2(Index index){this.index = index;}

    @Override
    public void run() {
        while (index.getIndex() <= 10){
            while (index.getThreadNumber() != 2) {}
            synchronized (index) {
                System.out.print(index.getIndex() + " ");
                index.incrementallyAccendIndex();
                index.setThreadNumber((short)1);
            }
        }
        while (index.getIndex() >= 1){
            while (index.getThreadNumber() != 2) {}
            synchronized (index) {
                System.out.print(index.getIndex() + " ");
                index.incrementallyDeccendIndex();
                index.setThreadNumber((short)1);
            }
        }
    }
}
