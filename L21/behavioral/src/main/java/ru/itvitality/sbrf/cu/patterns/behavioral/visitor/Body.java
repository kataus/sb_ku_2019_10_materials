package ru.itvitality.sbrf.cu.patterns.behavioral.visitor;

class Body implements CarElement {
  @Override
  public void accept(CarElementVisitor visitor) {
    visitor.visit(this);
  }
}
