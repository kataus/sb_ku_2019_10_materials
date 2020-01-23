package ru.itvitality.sbrf.cu.patterns.behavioral.mediator;

import java.awt.event.ActionListener;
import javax.swing.*;

//A concrete colleague
class BtnView extends JButton implements Command {

  Mediator med;

  BtnView(ActionListener al, Mediator m) {
    super("View");
    addActionListener(al);
    med = m;
    med.registerView(this);
  }

  public void execute() {
    med.view();
  }

}
