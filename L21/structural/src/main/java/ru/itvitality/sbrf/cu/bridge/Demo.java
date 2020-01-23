package ru.itvitality.sbrf.cu.bridge;

/**
 * @author sergey
 * created on 16.01.19.
 */
public class Demo {
  public static void main(String[] args) {
    Card card1 = new CreditCard(new VisaPS());
    card1.info();

    Card card2 = new DebitCard(new MastercardPS());
    card2.info();
  }
}
