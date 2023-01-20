package nl.ing.java.rocks;

public interface PersistencePort<T> {

  T persist(T input);
}
