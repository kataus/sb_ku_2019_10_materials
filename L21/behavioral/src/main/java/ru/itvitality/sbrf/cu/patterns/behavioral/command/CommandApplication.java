package ru.itvitality.sbrf.cu.patterns.behavioral.command;

public class CommandApplication {
  public static void main(final String[] args) {
    Light lamp = new Light();

    Command switchUp = new FlipUpCommand(lamp);
    Command switchDown = new FlipDownCommand(lamp);

    Switch mySwitch = new Switch();

    mySwitch.storeAndExecute(switchUp);
    mySwitch.storeAndExecute(switchDown);
    mySwitch.storeAndExecute(switchUp);
    mySwitch.storeAndExecute(switchDown);
    System.out.println("------");
    mySwitch.undo();
    mySwitch.undo();
    mySwitch.undo();
  }
}
