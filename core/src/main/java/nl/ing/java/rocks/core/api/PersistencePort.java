package nl.ing.java.rocks.core.api;

public interface PersistencePort<T> {

  T persist(T input);
}
