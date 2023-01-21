package nl.ing.java.rocks.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

import nl.ing.java.rocks.core.api.PersistencePort;
import nl.ing.java.rocks.entities.Note;
import nl.ing.java.rocks.testutil.NoteFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NoteBodyUpdateHandlerTest {

  @Mock
  private PersistencePort<Note> persistencePort;
  @InjectMocks
  private NoteBodyUpdateHandler noteBodyUpdateHandler;

  @Test
  void handle_shouldMutateBody() {
    var input = NoteFactory.createValidNoteDto();
    var expected = "JAVA 11 ROCKS!";
    assertThat(input.getBody()).isNotEqualTo(expected);
    noteBodyUpdateHandler.handle(input);
    verify(persistencePort).persist(argThat((Note note) -> note.getBody().equals(expected)));
  }
}
