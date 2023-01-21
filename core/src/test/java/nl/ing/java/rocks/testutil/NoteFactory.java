package nl.ing.java.rocks.testutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import nl.ing.java.rocks.core.NoteDto;
import nl.ing.java.rocks.core.Resources;
import nl.ing.java.rocks.entities.Note;

public final class NoteFactory {

  public static final String VALID_EMAIL = "info@compilit.com";
  public static final String TEST_HEADING = "test";
  public static final String TEST_BODY = "java rocks!";

  public static Note createValidNote() {
    return new Note(VALID_EMAIL, VALID_EMAIL, TEST_HEADING, TEST_BODY);
  }

  public static String createValidNoteDtoString() {
    return Resources.getResourceAsString("valid.xml");
  }

  public static NoteDto createValidNoteDto() {
    return read("valid.xml");
  }

  public static NoteDto createInvalidNoteDto() {
    return read("invalid.xml");
  }

  public static String createInvalidNoteDtoString() {
    return TEST_HEADING;
  }

  private static NoteDto read(String source) {
    try {
      return new XmlMapper().readValue(Resources.getResourceAsString(source), NoteDto.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

}
