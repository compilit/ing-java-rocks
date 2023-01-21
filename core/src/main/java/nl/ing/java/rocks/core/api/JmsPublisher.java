package nl.ing.java.rocks.core.api;

public interface JmsPublisher<T> {
  void publish(T input);
}
