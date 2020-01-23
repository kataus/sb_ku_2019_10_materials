package ru.itvitality.sbrf.cu.patterns.behavioral.command;

/**
 * The Command interface
 */
@FunctionalInterface
public interface Command {
  void execute();

  //  void m1();

  default void m2() {
    System.out.println("1");
  }


}
