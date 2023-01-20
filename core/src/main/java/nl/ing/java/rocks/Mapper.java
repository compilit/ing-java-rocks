package nl.ing.java.rocks;

public interface Mapper<I, O> {
  O map(I input);
}
