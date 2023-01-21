package nl.ing.java.rocks.core;

public class JavaRocksException extends RuntimeException {

  public JavaRocksException(Exception e) {
    super(e);
  }
}
