package ru.itvitality.sbrf.cu.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PingPong {
  private static Logger logger = LoggerFactory.getLogger(PingPong.class);
  private String last = "PONG";

  public static void main(String[] args) {
    PingPong pingPong = new PingPong();
    new Thread(() -> pingPong.action("ping")).start();
    new Thread(() -> pingPong.action("PONG")).start();
  }

  private static void sleep() {
    try {
      Thread.sleep(1_000);
    } catch (InterruptedException e) {
      logger.error(e.getMessage());
      Thread.currentThread().interrupt();
    }
  }

  private synchronized void action(String message) {
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      try {
        //spurious wakeup
        while (last.equals(message)) {
          this.wait();
        }

        logger.info(message);
        last = message;
        sleep();
        notifyAll();

      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
        throw new NotInterestingException(ex);
      }
    }
  }

  private class NotInterestingException extends RuntimeException {
    NotInterestingException(InterruptedException ex) {
      super(ex);
    }
  }
}
