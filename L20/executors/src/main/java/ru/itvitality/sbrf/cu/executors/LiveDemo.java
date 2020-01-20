package ru.itvitality.sbrf.cu.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RecursiveTask;

public class LiveDemo {

  private final ExecutorService executorService = Executors.newFixedThreadPool(1000);

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    LiveDemo liveDemo = new LiveDemo();
    long before = System.currentTimeMillis();
    int fibonacci = liveDemo.fibonacciExecutors(20);
    long after = System.currentTimeMillis();
    System.out.println(fibonacci);
    System.out.println(after - before);
  }

  public int fibonacci(int n) {
    if (n <= 1) {
      return n;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
  }

  public int fibonacciFutureTask(int n) throws ExecutionException, InterruptedException {
    if (n <= 1) {
      return n;
    }
    FutureTask<Integer> task = new FutureTask<>(() -> fibonacciFutureTask(n - 1));
    FutureTask<Integer> task2 = new FutureTask<>(() -> fibonacciFutureTask(n - 2));

    task.run();
    task2.run();

    return task.get() + task2.get();
  }

  public int fibonacciExecutors(int n) throws ExecutionException, InterruptedException {
    if (n <= 1) {
      return n;
    }

    Future<Integer> future = executorService.submit(() -> fibonacciFutureTask(n - 1));
    Future<Integer> future2 = executorService.submit(() -> fibonacciFutureTask(n - 2));

    return future.get() + future2.get();
  }

  public int fibonacciForkJoinPool(int n) {
    if (n <= 1) {
      return n;
    }

    FibonacciTask fibonacciTask = new FibonacciTask(n);
    ForkJoinTask<Integer> fork = fibonacciTask.fork();

    return fork.join();
  }

  public static class FibonacciTask extends RecursiveTask<Integer> {

    private final int n;

    public FibonacciTask(int n) {
      this.n = n;
    }

    @Override
    protected Integer compute() {
      if (n <= 1) {
        return n;
      }

      ForkJoinTask<Integer> fork = new FibonacciTask(n - 1).fork();
      ForkJoinTask<Integer> fork2 = new FibonacciTask(n - 2).fork();

      return fork.join() + fork2.join();
    }
  }


}
