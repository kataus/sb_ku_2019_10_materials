package ru.itvitality.sbrf.cu.patterns.behavioral.command;

/**
 * The Command for turning on the light - ConcreteCommand #1
 */
public class FlipUpCommand implements Command {
  private final Light light;

  public FlipUpCommand(Light light) {
    this.light = light;
  }

  @Override    // Command
  public void execute() {
    light.turnOn();
  }
}

