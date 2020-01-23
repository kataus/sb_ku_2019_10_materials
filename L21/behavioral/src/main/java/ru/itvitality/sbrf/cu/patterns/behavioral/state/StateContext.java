package ru.itvitality.sbrf.cu.patterns.behavioral.state;

class StateContext {
  private State myState;

  StateContext() {
    setState(new StateLowerCase());
  }

  /**
   * Setter method for the state.
   * Normally only called by classes implementing the State interface.
   *
   * @param newState the new state of this context
   */
  void setState(State newState) {
    myState = newState;
  }

  public void writeName(String name) {
    myState.writeName(this, name);
  }
}
