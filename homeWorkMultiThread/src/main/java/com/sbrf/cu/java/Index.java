package com.sbrf.cu.java;
import java.lang.Math;

public class Index {
    private int index;
    private short threadNumber;
    Index() {
        this.threadNumber=1;
        this.index = 1;
    }
    int getIndex(){
        return index;
    }
    void incrementallyAccendIndex(){ index++;}
    synchronized short getThreadNumber(){return threadNumber;}
    void setThreadNumber(short threadNumber){this.threadNumber=threadNumber;}
    void incrementallyDeccendIndex(){ index--;}
    void deccendIndex(short i){index-=i;}

}
