package nl.ing.java.rocks.api;

public class InvalidNoteFormatException extends RuntimeException {

  InvalidNoteFormatException(String message) {
    super(message);
  }
}
