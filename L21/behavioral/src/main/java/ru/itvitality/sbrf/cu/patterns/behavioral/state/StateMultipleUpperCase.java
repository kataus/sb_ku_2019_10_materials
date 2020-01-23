package ru.itvitality.sbrf.cu.patterns.behavioral.state;

class StateMultipleUpperCase implements State {
  /* Counter local to this state */
  private int count = 0;

  @Override
  public void writeName(StateContext context, String name) {
    System.out.println(name.toUpperCase());
    /* Change state after StateMultipleUpperCase's writeName() gets invoked twice */
    if (++count > 1) {
      context.setState(new StateLowerCase());
    }
  }
}
