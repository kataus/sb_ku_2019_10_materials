package vavr;

import io.vavr.control.Try;
import java.util.stream.Stream;

public class TryDemo {

  private boolean isException;

  public static void main(String[] args) {
    new TryDemo().go();
  }


  private String exception() {
    if (isException) {
      throw new RuntimeException("demo exception");
    }
    return "Ok";
  }

  private Try<String> exceptionTry() {
    return Try.of(() -> {
      if (isException) {
        throw new RuntimeException("demo exception");
      }
      return "Ok";
    });


  }

  private void go() {
    String value;
    try {
      value = exception() + "+Plus";

      Stream.of(exception()).map(val -> "!" + val.toUpperCase()).forEach(System.out::println);
    } catch (Exception ex) {
      value = ex.getMessage();
    }
    System.out.println(value);

    //////////////////////////////////

    String valueTryStr = exceptionTry().fold(Throwable::getMessage, val -> val + "+Plus");
    System.out.println(valueTryStr);

    stream(exceptionTry()).map(val -> "!" + val.toUpperCase()).forEach(System.out::println);
  }

  private <T> Stream<T> stream(Try<T> t) {
    return t.isSuccess() ? Stream.of(t.get()) : Stream.empty();
  }

}
