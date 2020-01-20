package ru.itvitality.sbrf.cu.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitorDemo {
  private static final int LIMIT = 100_000_000;
  private static Logger logger = LoggerFactory.getLogger(MonitorDemo.class);
  private final Object monitor = new Object();
  private int count = 0;

  public static void main(String[] args) throws InterruptedException {
    MonitorDemo counter = new MonitorDemo();
    counter.go();
  }

  private static synchronized void inc1(MonitorDemo demo) {
    for (int i = 0; i < LIMIT; i++) {
      demo.count++;
    }
  }

  //ошибочное импользование мониторов - у каждого потока свой монитор.
  private void inc2() {
    synchronized (monitor) {
      for (int i = 0; i < LIMIT; i++) {
        count++;
      }
    }
  }

  private synchronized void inc3() {
    for (int i = 0; i < LIMIT; i++) {
      count++;
    }
  }

  private void go() throws InterruptedException {
    Thread thread1 = new Thread(() -> inc1(this));
    Thread thread2 = new Thread(this::inc2);
    Thread thread3 = new Thread(this::inc3);

    thread1.start();
    thread2.start();
    thread3.start();

    thread1.join();
    thread2.join();
    thread3.join();
    logger.info("CounterBroken: {}", count);
  }
}
