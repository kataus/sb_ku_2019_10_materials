package com.sbrf.cu.java;

public class CustomThread1 implements Runnable{
    private Index index;
    private final short thrdNumber;
    private final short thrdNextOrderNumber;
    CustomThread1(Index index, short thrdNumber, short thrdNextOrderNumber){
        this.index=index;
        this.thrdNumber = thrdNumber;
        this.thrdNextOrderNumber = thrdNextOrderNumber;
    }

    @Override
    public void run() {
       for(byte i=1;i<20;i++){
           while (index.getThreadNumber()!=thrdNumber){}
           synchronized (index) {
               System.out.print(index.getFuncInterface().forAbs(i));
               index.setThreadNumber(thrdNextOrderNumber);
            }
        }
    }
}
