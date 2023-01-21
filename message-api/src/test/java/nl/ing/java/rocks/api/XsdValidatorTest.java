package nl.ing.java.rocks.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.util.stream.Stream;
import javax.xml.validation.Validator;
import nl.ing.java.rocks.core.NoteDto;
import nl.ing.java.rocks.testutil.NoteFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xml.sax.SAXException;

@ExtendWith(MockitoExtension.class)
class XsdValidatorTest {

  @Mock
  private Validator validator;
  @Mock
  private XmlMapper xmlMapper;
  @InjectMocks
  private XsdValidator xsdValidator;

  public static Stream<Arguments> validInput() {
    return Stream.of(
      Arguments.arguments(NoteFactory.createValidNoteDto())
      //todo: add more test cases
    );
  }

  public static Stream<Arguments> invalidInput() {
    return Stream.of(
      Arguments.arguments(NoteFactory.createInvalidNoteDto())
      //todo: add more test cases
    );
  }

  @ParameterizedTest
  @MethodSource("validInput")
  void validate_validInput_shouldAcceptMessage(NoteDto note) throws JsonProcessingException {
    when(xmlMapper.writeValueAsString(any())).thenReturn(NoteFactory.createValidNoteDtoString());
    Assertions.assertThatNoException().isThrownBy(() -> xsdValidator.validate(note));
  }

  @ParameterizedTest
  @MethodSource("invalidInput")
  void validate_invalidInput_shouldThrowException(NoteDto note) throws IOException, SAXException {
    when(xmlMapper.writeValueAsString(any())).thenReturn("test");
    doThrow(new InvalidNoteFormatException("test")).when(validator).validate(any());
    Assertions.assertThatThrownBy(() -> xsdValidator.validate(note))
              .isInstanceOf(InvalidNoteFormatException.class);
  }
}