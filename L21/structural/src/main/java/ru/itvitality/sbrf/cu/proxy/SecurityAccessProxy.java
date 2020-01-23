package ru.itvitality.sbrf.cu.proxy;

/**
 * @author sergey
 * created on 16.01.19.
 */
public class SecurityAccessProxy implements SecurityAccess {

  private final SecurityAccess securityAccess;

  public SecurityAccessProxy(SecurityAccess securityAccess) {
    this.securityAccess = securityAccess;
  }

  @Override
  public void access() {
    System.out.println("before");
    securityAccess.access();
    System.out.println("after");
  }
}
