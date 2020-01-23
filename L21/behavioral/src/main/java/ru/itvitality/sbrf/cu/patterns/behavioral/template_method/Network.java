package ru.itvitality.sbrf.cu.patterns.behavioral.template_method;

/**
 * Base class of social network.
 */
public abstract class Network {

  protected String userName;
  protected String password;

  /**
   * Publish the data to whatever network.
   */
  public boolean post(String message) {
    // Authenticate before posting. Every network uses a different
    // authentication method.
    if (logIn(userName, password)) {
      // Send the post data.
      boolean result = sendData(message.getBytes());
      logOut();
      return result;
    }
    return false;
  }

  protected void simulateNetworkLatency() {
    try {
      int i = 0;
      System.out.println();
      while (i < 10) {
        System.out.print(".");
        Thread.sleep(500);
        i++;
      }
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }

  abstract boolean logIn(String userName, String password);

  abstract boolean sendData(byte[] data);

  abstract void logOut();
}
