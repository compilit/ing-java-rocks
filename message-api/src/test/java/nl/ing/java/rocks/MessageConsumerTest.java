package nl.ing.java.rocks;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Stream;
import nl.ing.java.rocks.testutil.NoteFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

public class MessageConsumerTest {

  private final Handler<Note> noteHandler = Mockito.mock(Handler.class);
  private final Validator<String> noteValidator = Mockito.mock(Validator.class);
  private final ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);
  private final MessageConsumer messageConsumer = new MessageConsumer(noteHandler, noteValidator, objectMapper);
  public static Stream<Arguments> validInput() {
    return Stream.of(
      Arguments.arguments(NoteFactory.createValidNoteDto())
    );
  }

  public static Stream<Arguments> invalidInput() {
    return Stream.of(
      Arguments.arguments(NoteFactory.createInvalidNoteDto())
    );
  }

  @ParameterizedTest
  @MethodSource("validInput")
  void consumeMessage_validInput_shouldAcceptMessage(String note) {
    Assertions.assertThatNoException().isThrownBy(() -> messageConsumer.consume(note));
  }

  @ParameterizedTest
  @MethodSource("invalidInput")
  void consumeMessage_invalidInput_shouldThrowException(String note) {
    doThrow(new InvalidNoteFormatException("test")).when(noteValidator).validate(note);
    Assertions.assertThatThrownBy(() -> messageConsumer.consume(note))
      .isInstanceOf(InvalidNoteFormatException.class);
  }
}
