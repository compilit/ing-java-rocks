package nl.ing.java.rocks.application;

import nl.ing.java.rocks.core.NoteDto;
import nl.ing.java.rocks.core.api.JmsPublisher;
import nl.ing.java.rocks.testutil.NoteFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JavaRocksApplication.class)
public class JavaRocksIntegrationTest {

  @Autowired
  JmsPublisher<NoteDto> noteDtoJmsPublisher;
  @Test
  void run() {
    var validNoteDto = NoteFactory.createValidNoteDto();
    noteDtoJmsPublisher.publish(validNoteDto);
  }
}
