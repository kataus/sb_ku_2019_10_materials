package ru.itvitality.sbrf.cu.patterns.behavioral.mediator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class MediatorApplication extends JFrame implements ActionListener {

  private Mediator med = new ParticipantMediator();

  private MediatorApplication() {
    JPanel p = new JPanel();
    p.add(new BtnView(this, med));
    p.add(new BtnBook(this, med));
    p.add(new BtnSearch(this, med));
    getContentPane().add(new LblDisplay(med), "North");
    getContentPane().add(p, "South");
    setSize(400, 200);
    setVisible(true);
  }

  public static void main(String[] args) {
    new MediatorApplication();
  }

  public void actionPerformed(ActionEvent ae) {
    Command comd = (Command) ae.getSource();
    comd.execute();
  }

}
