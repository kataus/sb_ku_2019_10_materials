package ru.itvitality.cu.thread;

import java.util.concurrent.TimeUnit;

public class ThreadMonitoringDemo {
  public static void main(String[] args) {
    Thread thread = new Thread(() -> {
      while (true) {
        System.out.println("Hello from " + Thread.currentThread().getName());
        sleep();
      }
    });
    thread.setName("Thread-Monitoring-Demo");
    thread.start();
  }

  private static void sleep() {
    try {
      Thread.sleep(TimeUnit.SECONDS.toMillis(10));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
