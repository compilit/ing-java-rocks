package nl.ing.java.rocks.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nl.ing.java.rocks.api.InvalidNoteFormatException;
import nl.ing.java.rocks.core.api.JmsConsumer;
import nl.ing.java.rocks.core.api.RetrievalPort;
import nl.ing.java.rocks.entities.Note;
import nl.ing.java.rocks.testutil.NoteFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = JavaRocksApplication.class)
public class JavaRocksComponentTest extends IntegrationTest {

  //Using the in-memory database and no actual MQ
  @Autowired
  JmsConsumer<GenericMessage<String>> noteDtoJmsConsumer;
  @Autowired
  RetrievalPort<List<Note>, String> noteRetrievalPort;

  @Test
  void consume_validNoteDto_shouldAddEntryInDatabaseWithCorrectBody() {
    var results = noteRetrievalPort.retrieveBy("Reminder");
    assertThat(results).hasSize(0);
    var validNoteDto = NoteFactory.createValidNoteDtoString();

    noteDtoJmsConsumer.consume(new GenericMessage<>(validNoteDto));

    results = noteRetrievalPort.retrieveBy("Reminder");

    assertThat(results).hasSize(1);
    assertThat(results.get(0).getBody()).isEqualTo("JAVA 11 ROCKS!");
  }

  @Test
  void consume_invalidNoteDto_shouldThrowException() {
    var results = noteRetrievalPort.retrieveBy("Reminder");
    assertThat(results).hasSize(0);
    var validNoteDto = NoteFactory.createInvalidNoteDtoString();

    assertThatThrownBy(() -> noteDtoJmsConsumer.consume(new GenericMessage<>(validNoteDto)))
      .isInstanceOf(InvalidNoteFormatException.class);
    results = noteRetrievalPort.retrieveBy("Reminder");
    assertThat(results).hasSize(0);
  }
}
