package ru.itvitality.sbrf.cu.patterns.behavioral.visitor;

interface CarElementVisitor {
  void visit(Body body);

  void visit(Car car);

  void visit(Engine engine);

  void visit(Wheel wheel);
}
