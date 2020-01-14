package ru.itvitality.cu.thread;

public class ThreadDemo {
  public static void main(String[] args) throws InterruptedException {
//       case1();
    case2();
  }

  private static void case1() throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + ". Main program started");

    Thread thread = new Thread(
        () -> System.out.println("from thread:" + Thread.currentThread().getName()));
//    thread.setDaemon(true);
    thread.start();

//    Thread.currentThread().isInterrupted()

//    Thread.sleep(1_000);

    System.out.println(Thread.currentThread().getName() + ". Main program finished");
  }

  private static void case2() {
    System.out.println(Thread.currentThread().getName() + ". Main program started");

    CustomThread thread = new CustomThread();
    thread.start();

    System.out.println(Thread.currentThread().getName() + ". Main program finished");
  }


  static class CustomThread extends Thread {
    @Override
    public void run() {
      System.out.println("from thread:" + currentThread().getName());
    }
  }
}
