package vavr;

import io.vavr.Lazy;

public class LazyDemo {
  public static void main(String[] args) {
    new LazyDemo().go();
  }

  private void go() {
    System.out.println("debug 100");

    Lazy<String> lazy = Lazy.of(() -> {
      System.out.println("Some lazy calculation");
      return "LazyVal";
    });

    System.out.println("debug 200");

    String value = lazy.get();
    System.out.println(value);

    System.out.println("debug 300");

    String value2 = lazy.get();
    System.out.println(value2);
  }
}
