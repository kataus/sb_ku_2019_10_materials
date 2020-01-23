package ru.itvitality.sbrf.cu.patterns.behavioral.mediator;

import java.awt.*;
import javax.swing.*;

class LblDisplay extends JLabel {

  Mediator med;

  LblDisplay(Mediator m) {
    super("Just start...");
    med = m;
    med.registerDisplay(this);
    setFont(new Font("Arial", Font.BOLD, 24));
  }

}
