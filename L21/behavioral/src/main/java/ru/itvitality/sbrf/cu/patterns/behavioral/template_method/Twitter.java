package ru.itvitality.sbrf.cu.patterns.behavioral.template_method;

import lombok.AllArgsConstructor;

/**
 * Class of social network
 */
@AllArgsConstructor
public class Twitter extends Network {

  public boolean logIn(String userName, String password) {
    System.out.println("\nChecking user's parameters");
    System.out.println("Name: " + this.userName);
    System.out.print("Password: ");
    for (int i = 0; i < this.password.length(); i++) {
      System.out.print("*");
    }
    simulateNetworkLatency();
    System.out.println("\n\nLogIn success on Twitter");
    return true;
  }

  public boolean sendData(byte[] data) {
    boolean messagePosted = true;
    if (messagePosted) {
      System.out.println("Message: '" + new String(data) + "' was posted on Twitter");
      return true;
    } else {
      return false;
    }
  }

  public void logOut() {
    System.out.println("User: '" + userName + "' was logged out from Twitter");
  }

}
