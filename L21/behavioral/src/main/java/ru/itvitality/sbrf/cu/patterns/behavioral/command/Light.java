package ru.itvitality.sbrf.cu.patterns.behavioral.command;

/**
 * The Receiver class
 */
public class Light {
  public void turnOn() {
    System.out.println("The light is on");
  }

  public void turnOff() {
    System.out.println("The light is off");
  }
}
