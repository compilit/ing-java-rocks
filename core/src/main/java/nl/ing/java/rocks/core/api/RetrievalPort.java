package nl.ing.java.rocks.core.api;

public interface RetrievalPort<T, ID> {
  T retrieveBy(ID identifyingProperty);
}
