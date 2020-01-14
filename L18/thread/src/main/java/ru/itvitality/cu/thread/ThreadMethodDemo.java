package ru.itvitality.cu.thread;

import java.util.concurrent.TimeUnit;

public class ThreadMethodDemo {
  public static void main(String[] args) throws InterruptedException {
    System.out.println("starting");

    Thread thread = new Thread(
        () -> {
          boolean stop = false;
          while (!stop) {
            System.out.println("I am: " + Thread.currentThread().getName() + " state: "
                                   + Thread.currentThread().getState());
            stop = sleepAndStop(1);
            Thread.onSpinWait(); // "новая фича"
          }

        });
    thread.setName("Named-thread");
    thread.setDaemon(false);
    System.out.println("state:" + thread.getState());

    thread.start();

    sleep(10);
    System.out.println("interrupting");
    thread.interrupt();

    thread.join();

    System.out.println("finished");
  }

  private static boolean sleepAndStop(int seconds) {
    try {
      Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
      return false;
    } catch (InterruptedException e) {
      System.out.println("somebody is trying to stop us, Ok");
      return true;
    }
  }

  private static void sleep(int seconds) {
    try {
      Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

  }
}
