package ru.itvitality.sbrf.cu.patterns.behavioral.command;

import java.util.Deque;
import java.util.LinkedList;

/**
 * The Invoker class
 */
public class Switch {
  private final Deque<Command> history = new LinkedList<>();

  public void storeAndExecute(Command cmd) {
    this.history.add(cmd); // optional
    cmd.execute();
  }

  public void undo() {
    if (history.size() > 1) {
      history.removeLast();
      history.getLast().execute();
    }
  }
}

