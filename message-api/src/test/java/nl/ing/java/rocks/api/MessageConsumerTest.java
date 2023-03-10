package nl.ing.java.rocks.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.util.stream.Stream;
import nl.ing.java.rocks.core.NoteDto;
import nl.ing.java.rocks.core.api.Handler;
import nl.ing.java.rocks.core.api.Validator;
import nl.ing.java.rocks.testutil.NoteFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.messaging.support.GenericMessage;

public class MessageConsumerTest {

  private final Handler<NoteDto> noteHandler = Mockito.mock(Handler.class);
  private final Validator<NoteDto> noteValidator = Mockito.mock(Validator.class);
  private final XmlMapper xmlMapper = Mockito.mock(XmlMapper.class);
  private final MessageConsumer messageConsumer = new MessageConsumer(noteHandler, noteValidator, xmlMapper);

  public static Stream<Arguments> validInput() {
    return Stream.of(
      Arguments.arguments(NoteFactory.createValidNoteDtoString())
    );
  }

  public static Stream<Arguments> invalidInput() {
    return Stream.of(
      Arguments.arguments(NoteFactory.createInvalidNoteDtoString())
    );
  }

  @ParameterizedTest
  @MethodSource("validInput")
  void consumeMessage_validInput_shouldAcceptMessage(String note) {
    Assertions.assertThatNoException().isThrownBy(() -> messageConsumer.consume(new GenericMessage<>(note)));
  }

  @ParameterizedTest
  @MethodSource("invalidInput")
  void consumeMessage_invalidInput_shouldThrowException(String note) {
    doThrow(new InvalidNoteFormatException("test")).when(noteValidator).validate(any());
    Assertions.assertThatThrownBy(() -> messageConsumer.consume(new GenericMessage<>(note)))
              .isInstanceOf(InvalidNoteFormatException.class);
  }
}
