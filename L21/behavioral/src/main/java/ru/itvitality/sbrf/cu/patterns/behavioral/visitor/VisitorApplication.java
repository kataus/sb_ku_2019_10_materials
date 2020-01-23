package ru.itvitality.sbrf.cu.patterns.behavioral.visitor;

public class VisitorApplication {
  public static void main(final String[] args) {
    Car car = new Car();

    car.accept(new CarElementPrintVisitor());
    car.accept(new CarElementDoVisitor());
  }
}
