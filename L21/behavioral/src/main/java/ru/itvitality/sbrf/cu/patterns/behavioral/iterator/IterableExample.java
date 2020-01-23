package ru.itvitality.sbrf.cu.patterns.behavioral.iterator;

import java.util.Arrays;
import java.util.Collection;

public class IterableExample {
  public static void main(String[] args) {
    final Collection<String> weasleys = Arrays.asList(
        "Arthur", "Molly", "Bill", "Charlie",
        "Percy", "Fred", "George", "Ron", "Ginny"
    );

    Iterable<String> family = new Family<>(weasleys);

    for (String name : family) {
      System.out.println(name + " Weasley");
    }
  }
}
