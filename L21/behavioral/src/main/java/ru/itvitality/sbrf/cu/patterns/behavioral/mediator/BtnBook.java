package ru.itvitality.sbrf.cu.patterns.behavioral.mediator;

import java.awt.event.ActionListener;
import javax.swing.*;

//A concrete colleague
class BtnBook extends JButton implements Command {

  Mediator med;

  BtnBook(ActionListener al, Mediator m) {
    super("Book");
    addActionListener(al);
    med = m;
    med.registerBook(this);
  }

  public void execute() {
    med.book();
  }

}
