package ru.itvitality.sbrf.cu.executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {
  private static Logger logger = LoggerFactory.getLogger(CompletableFutureDemo.class);

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    new CompletableFutureDemo().go();
  }

  private static void sleep() {
    try {
      Thread.sleep(TimeUnit.SECONDS.toMillis(3));
    } catch (InterruptedException e) {
      logger.error(e.getMessage());
      Thread.currentThread().interrupt();
    }
  }

  private void go() throws ExecutionException, InterruptedException {
    CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> task(1));
    logger.info("thread is not blocked");
    logger.info("result:{}", future1.get());

    CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> task(2));
    future2.thenAccept(val -> logger.info("result:{}", val));
    future2.join();

    CompletableFuture<String> future3 = CompletableFuture.supplyAsync(this::errorAction);
    future3.exceptionally(Throwable::getMessage).thenAccept(msg -> logger.info("msg:{}", msg));

    CompletableFuture<String> futureT1 = CompletableFuture.supplyAsync(() -> task(100));
    CompletableFuture<String> futureT2 = CompletableFuture.supplyAsync(() -> task(200));
    CompletableFuture<Void> joinedResult = futureT1.thenAcceptBoth(futureT2, (s1, s2) -> logger.info("joinedResult: {}, {}", s1, s2));
    joinedResult.join();

//        CompletableFuture<String> futureT1 = CompletableFuture.supplyAsync(() -> task(100));
//        CompletableFuture<String> futureT2 = CompletableFuture.supplyAsync(() -> task(200));
//        CompletableFuture<Void> firstResult = futureT1.acceptEither(futureT2, s -> logger.info("firstResult: {}", s));
//        firstResult.join();
  }

  private String errorAction() {
    throw new TestException("error for Test");
  }

  private String task(int id) {
    sleep();
    logger.info("task is done: {}", id);
    return "done" + id;
  }

  private class TestException extends RuntimeException {
    TestException(String msg) {
      super(msg);
    }
  }
}
