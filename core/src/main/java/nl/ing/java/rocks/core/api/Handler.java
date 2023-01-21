package nl.ing.java.rocks.core.api;

public interface Handler<T> {

  void handle(T input);
}
