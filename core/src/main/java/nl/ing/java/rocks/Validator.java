package nl.ing.java.rocks;

import java.io.Serializable;

public interface Validator<T extends Serializable> {
  void validate(T input);
}
