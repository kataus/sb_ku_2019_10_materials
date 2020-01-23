package ru.itvitality.sbrf.cu.patterns.behavioral.observer;

import java.util.Observable;
import java.util.Scanner;

public class Subject extends Observable implements Runnable {
  public void run() {
    while (true) {
      String response = new Scanner(System.in).next();
      setChanged();
      notifyObservers(response);
    }
  }
}
