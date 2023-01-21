package nl.ing.java.rocks.core.api;

public interface JmsConsumer<T> {
  void consume(T input);
}
