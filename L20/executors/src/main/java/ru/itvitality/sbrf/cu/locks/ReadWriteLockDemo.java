package ru.itvitality.sbrf.cu.locks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
  private static Logger logger = LoggerFactory.getLogger(ReadWriteLockDemo.class);
  private final ReadWriteLock lock = new ReentrantReadWriteLock();

  private int count = 0;

  public static void main(String[] args) {
    new ReadWriteLockDemo().go();
  }

  private static void sleep() {
    try {
      Thread.sleep(TimeUnit.SECONDS.toMillis(1));
    } catch (InterruptedException e) {
      logger.error(e.getMessage());
      Thread.currentThread().interrupt();
    }
  }

  private void go() {
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        Lock writeLock = this.lock.writeLock();
        writeLock.lock();
        logger.info("Write lock");
        count++;
        logger.info("Write: {}", count);
        writeLock.unlock();
        logger.info("Write unlock");
        sleep();
      }
    });

    Runnable reader = () -> {
      while (t1.isAlive()) {
        Lock readLock = this.lock.readLock();
        readLock.lock();
        logger.info("Read from critical section {}", count);
        sleep();
        readLock.unlock();
        logger.info("Read unlock", count);
      }
    };
    Thread t2 = new Thread(reader);
    Thread t3 = new Thread(reader);
    Thread t4 = new Thread(reader);

    t1.setName("writer");
    t2.setName("reader");
    t3.setName("reader2");
    t4.setName("reader3");

    t1.start();
    t2.start();
    t3.start();
    t4.start();
  }

}
