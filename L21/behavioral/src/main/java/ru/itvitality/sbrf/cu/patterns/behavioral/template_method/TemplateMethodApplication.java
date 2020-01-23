package ru.itvitality.sbrf.cu.patterns.behavioral.template_method;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Demo class. Everything comes together here.
 */
public class TemplateMethodApplication {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    Network network = null;
    System.out.print("Input user name: ");
    String userName = reader.readLine();
    System.out.print("Input password: ");
    String password = reader.readLine();

    // Enter the message.
    System.out.print("Input message: ");
    String message = reader.readLine();

    System.out.println("\nChoose social network for posting message.\n" +
                           "1 - Facebook\n" +
                           "2 - Twitter");
    int choice = Integer.parseInt(reader.readLine());

    // Create proper network object and send the message.
    //    switch (choice) {
    //      case 1:
    //        network = new Facebook(userName, password);
    //        break;
    //      case 2:
    //        network = new Twitter(userName, password);
    //        break;
    //      default:
    //        throw new RuntimeException("No such social network code: " + choice);
    //    }
    //    network.post(message);
  }
}

