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
           synchronized (index) {
               try{
                   if(index.getThreadNumber()!=thrdNumber)
                       index.wait();
               }
               catch (IllegalMonitorStateException | InterruptedException e){
                   System.out.println(e.getMessage());
               }
               System.out.print(index.getFuncInterface().forAbs(i));
               index.setThreadNumber(thrdNextOrderNumber);
               index.notify();
            }
        }
    }
}
