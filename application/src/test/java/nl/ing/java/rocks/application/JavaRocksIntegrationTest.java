package nl.ing.java.rocks.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import nl.ing.java.rocks.core.NoteDto;
import nl.ing.java.rocks.core.api.JmsConsumer;
import nl.ing.java.rocks.core.api.JmsPublisher;
import nl.ing.java.rocks.core.api.RetrievalPort;
import nl.ing.java.rocks.entities.Note;
import nl.ing.java.rocks.testutil.NoteFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.GenericMessage;

@SpringBootTest(classes = JavaRocksApplication.class)
public class JavaRocksIntegrationTest {

  //A very, very minimal integration test
  @Autowired
  JmsConsumer<GenericMessage<String>> noteDtoJmsConsumer;
  @Autowired
  JmsPublisher<NoteDto> noteDtoJmsPublisher;
  @Autowired
  RetrievalPort<List<Note>, String> noteRetrievalPort;

  @Test
  void consume_validNoteDto_shouldAddEntryInDatabaseWithCorrectBody() throws InterruptedException {
    var results = noteRetrievalPort.retrieveBy("Reminder");
    assertThat(results).hasSize(0);
    var validNoteDto = NoteFactory.createValidNoteDto();

    noteDtoJmsPublisher.publish(validNoteDto);

    Thread.sleep(1000); //due to the async and multi-threaded nature, I made this quick and dirty fix...
    results = noteRetrievalPort.retrieveBy("Reminder");

    assertThat(results).hasSize(1);
    assertThat(results.get(0).getBody()).isEqualTo("JAVA 11 ROCKS!");
  }
}
