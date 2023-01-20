package nl.ing.java.rocks.testutil;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import nl.ing.java.rocks.Note;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

public final class NoteFactory {

  public static final String VALID_EMAIL = "info@compilit.com";
  public static final String TEST_TEXT = "test";

  public static Note createValidNote() {
    return new Note(VALID_EMAIL, VALID_EMAIL, TEST_TEXT, TEST_TEXT);
  }

  public static String createValidNoteDto() {
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    Resource resource = resourceLoader.getResource("input.xml");
    return asString(resource);
  }

  public static String createInvalidNoteDto() {
    return TEST_TEXT;
  }

  private static String asString(Resource resource) {
    try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
      return FileCopyUtils.copyToString(reader);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

}
