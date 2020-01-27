package vavr;

import java.util.Optional;
import java.util.stream.Stream;

public class NullDemo {

  private boolean isNull = true;

  public static void main(String[] args) {
    new NullDemo().go();
  }

  private String someFunction() {
    return isNull ? null : "STR";
  }

  private Optional<String> someFunctionOprional() {
    return isNull ? Optional.empty() : Optional.of("STR");
  }

  private void go() {

    String value = someFunction();
    if (value == null) {
      System.out.println("value is null");
    } else {
      String valuePlus = value + "+PLUS";
      System.out.println(valuePlus);
    }


    if (value != null) {
      Stream.of(value).map(val -> "!" + val.toUpperCase()).forEach(System.out::println);
    }
    //////////////////////////////////

    Optional<String> valueOp = someFunctionOprional();
    valueOp.map(val -> val + "+PLUS")
        .ifPresentOrElse(System.out::println, () -> System.out.println("value is null"));


    valueOp.stream().map(val -> "!" + val.toUpperCase()).forEach(System.out::println);

  }
}
