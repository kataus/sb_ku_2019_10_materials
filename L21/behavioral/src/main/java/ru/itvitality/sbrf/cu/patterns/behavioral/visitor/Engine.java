package ru.itvitality.sbrf.cu.patterns.behavioral.visitor;

class Engine implements CarElement {
  @Override
  public void accept(CarElementVisitor visitor) {
    visitor.visit(this);
  }
}

