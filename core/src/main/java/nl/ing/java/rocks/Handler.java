package nl.ing.java.rocks;

public interface Handler<T> {
  void handle(T input);
}
