package com.sbrf.cu.java;

public class Index {
    private short threadNumber;
    private FuncInterface funcInterface;
    Index() {
        this.threadNumber=1;
    }
    synchronized short getThreadNumber(){return threadNumber;}
    synchronized void setThreadNumber(short threadNumber){this.threadNumber=threadNumber;}
    public void setFuncInterface(FuncInterface funcInterface){
        this.funcInterface=funcInterface;
    }
    synchronized public FuncInterface getFuncInterface() {
        return funcInterface;
    }
}
