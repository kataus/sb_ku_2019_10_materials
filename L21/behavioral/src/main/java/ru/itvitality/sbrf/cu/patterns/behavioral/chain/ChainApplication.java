package ru.itvitality.sbrf.cu.patterns.behavioral.chain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ChainApplication {
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private Server server;

  private void init() {
    server = new Server();
    server.register("admin@example.com", "admin_pass");
    server.register("user@example.com", "user_pass");

    // All checks are linked. Client can build various chains using the same
    // components.
    Middleware middleware = new RoleCheckMiddleware();
    middleware
        .linkWith(new ThrottlingMiddleware(2))
        .linkWith(new UserExistsMiddleware(server));

    // Server gets a chain from client code.
    server.setMiddleware(middleware);
  }

  public void main(String[] args) throws IOException {
    init();

    boolean success;
    do {
      System.out.print("Enter email: ");
      String email = reader.readLine();
      System.out.print("Input password: ");
      String password = reader.readLine();
      success = server.logIn(email, password);
    } while (!success);
  }
}
