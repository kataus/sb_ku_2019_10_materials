package ru.itvitality.sbrf.cu.patterns.behavioral.visitor;

interface CarElement {
  void accept(CarElementVisitor visitor);
}
