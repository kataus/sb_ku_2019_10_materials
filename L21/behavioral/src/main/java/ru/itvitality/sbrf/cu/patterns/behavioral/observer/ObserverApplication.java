package ru.itvitality.sbrf.cu.patterns.behavioral.observer;

public class ObserverApplication {
  public static void main(String[] args) {
    System.out.println("Enter Text: ");
    Subject eventSource = new Subject();

    eventSource.addObserver((obj, arg) -> {
      System.out.println("Received response: " + arg);
    });

    eventSource.addObserver((obj, arg) -> {
      System.out.println("[" + arg + "]");
    });

    new Thread(eventSource).start();
  }
}
