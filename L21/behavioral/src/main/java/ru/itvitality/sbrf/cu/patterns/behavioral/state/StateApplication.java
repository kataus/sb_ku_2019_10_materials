package ru.itvitality.sbrf.cu.patterns.behavioral.state;

public class StateApplication {
  public static void main(String[] args) {
    var sc = new StateContext();
    sc.writeName("Эники");
    sc.writeName("Беники");
    sc.writeName("Ели");
    sc.writeName("Вареники");
    sc.writeName("Трям!");
    for (int i = 0; i < 100; i++) {
      sc.writeName("Hello " + i);
    }
  }
}
