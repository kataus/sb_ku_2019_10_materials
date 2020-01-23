package ru.itvitality.sbrf.cu.proxy;

/**
 * @author sergey
 * created on 16.01.19.
 */
public class Demo {
  public static void main(String[] args) {
    SecurityAccess proxy = new SecurityAccessProxy(new SecurityAccessImpl());
    proxy.access();
  }
}
