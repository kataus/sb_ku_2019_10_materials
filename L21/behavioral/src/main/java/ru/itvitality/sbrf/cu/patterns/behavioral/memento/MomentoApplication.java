package ru.itvitality.sbrf.cu.patterns.behavioral.memento;

public class MomentoApplication {
  public static void main(String[] args) {
    Originator originator = new Originator();
    Caretaker caretaker = new Caretaker();

    originator.setState("on");
    System.out.printf("originator is %s\n", originator);
    caretaker.setMemento(
        originator.saveState());

    originator.setState("off");
    System.out.printf("originator is %s\n", originator);
    System.out.println("-------------");
    originator.restoreState(
        caretaker.getMemento());
    System.out.printf("originator is %s\n", originator);
  }
}
