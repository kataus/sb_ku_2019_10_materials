package ru.itvitality.sbrf.cu.locks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
  private static Logger logger = LoggerFactory.getLogger(ReentrantLockDemo.class);

  private final Lock lock = new ReentrantLock();

  public static void main(String[] args) {
    new ReentrantLockDemo().go();
  }

  private static void sleep() {
    try {
      Thread.sleep(TimeUnit.SECONDS.toMillis(10));
    } catch (InterruptedException e) {
      logger.error(e.getMessage());
      Thread.currentThread().interrupt();
    }
  }

  private void go() {
    Thread t1 = new Thread(this::criticalSection);
    t1.setName("t1");
    t1.start();

    Thread t2 = new Thread(this::criticalSection);
    t2.setName("t2");
    t2.start();
  }

  private void criticalSection() {
    logger.info("before critical section");
    lock.lock();
    try {
      logger.info("in the critical section");
      sleep();
    } finally {
      lock.unlock();
    }
    logger.info("after critical section");
  }
}
