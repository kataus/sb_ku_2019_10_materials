package ru.itvitality.sbrf.cu.patterns.behavioral.memento;

import java.util.Arrays;

public class Memento {
  private final byte[] state;

  public Memento(byte[] state) {
    this.state = Arrays.copyOf(state, state.length);
  }

  public byte[] getState() {
    return state;
  }
}
