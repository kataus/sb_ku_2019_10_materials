package ru.itvitality.cu.thread;

import java.util.concurrent.ThreadLocalRandom;

public class JoinDemo {
  public static void main(String[] args) throws InterruptedException {
//    freeRun();
     orderedRun();
  }

  private static void freeRun() throws InterruptedException {
    System.out.println("starting");

    Thread t1 = new Thread(() -> action("t1"));
    Thread t2 = new Thread(() -> action("t2"));
    Thread t3 = new Thread(() -> action("t3"));
    Thread t4 = new Thread(() -> action("t4"));
    Thread t5 = new Thread(() -> action("t5"));
    Thread t6 = new Thread(() -> action("t6"));
    Thread t7 = new Thread(() -> action("t7"));
    Thread t8 = new Thread(() -> action("t8"));
    Thread t9 = new Thread(() -> action("t9"));
    Thread t10 = new Thread(() -> action("t10"));

    t1.start();
    t2.start();
    t3.start();
    t4.start();
    t5.start();
    t6.start();
    t7.start();
    t8.start();
    t9.start();
    t10.start();

    t1.join();

    System.out.println("finished");
  }

  private static void orderedRun() throws InterruptedException {
    System.out.println("starting");

    Thread t1 = new Thread(() -> action("t1"));
    Thread t2 = new Thread(() -> action("t2"));
    Thread t3 = new Thread(() -> action("t3"));
    Thread t4 = new Thread(() -> action("t4"));
    Thread t5 = new Thread(() -> action("t5"));
    Thread t6 = new Thread(() -> action("t6"));
    Thread t7 = new Thread(() -> action("t7"));
    Thread t8 = new Thread(() -> action("t8"));
    Thread t9 = new Thread(() -> action("t9"));
    Thread t10 = new Thread(() -> action("t10"));

    t1.start();
    t1.join();
    t2.start();
    t2.join();
    t3.start();
    t3.join();
    t4.start();
    t4.join();
    t5.start();
    t5.join();
    t6.start();
    t6.join();
    t7.start();
    t7.join();
    t8.start();
    t8.join();
    t9.start();
    t9.join();
    t10.start();
    t10.join();


    System.out.println("finished");
  }


  private static void action(String str) {
    try {
      Thread.sleep(ThreadLocalRandom.current().nextInt(10, 100));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(str);
  }
}
