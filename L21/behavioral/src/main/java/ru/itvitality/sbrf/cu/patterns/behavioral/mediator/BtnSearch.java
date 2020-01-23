package ru.itvitality.sbrf.cu.patterns.behavioral.mediator;

import java.awt.event.ActionListener;
import javax.swing.*;

//A concrete colleague
class BtnSearch extends JButton implements Command {

  Mediator med;

  BtnSearch(ActionListener al, Mediator m) {
    super("Search");
    addActionListener(al);
    med = m;
    med.registerSearch(this);
  }

  public void execute() {
    med.search();
  }

}
