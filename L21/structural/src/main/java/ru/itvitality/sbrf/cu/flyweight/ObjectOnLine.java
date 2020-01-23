package ru.itvitality.sbrf.cu.flyweight;

/**
 * @author sergey
 * created on 16.01.19.
 */
public class ObjectOnLine {
  private final int x;

  private final HeavyCommonPart heavyCommonPart;

  ObjectOnLine(HeavyCommonPart heavyCommonPart, int x) {
    this.heavyCommonPart = heavyCommonPart;
    this.x = x;
  }

  @Override
  public String toString() {
    return "ObjectOnLine{" +
               "x=" + x +
               ", heavyCommonPart=" + heavyCommonPart +
               '}';
  }
}
