package nl.ing.java.rocks.core.api;

public interface Validator<T> {

  void validate(T input);
}
