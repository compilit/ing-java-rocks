package nl.ing.java.rocks.api;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.xml.transform.stream.StreamSource;
import nl.ing.java.rocks.core.Resources;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

@ExtendWith(MockitoExtension.class)
public class ValidatorTest {
  @Mock
  private MessageConsumerProperties messageConsumerProperties;
  @InjectMocks
  private MessageConsumerConfiguration messageConsumerConfiguration;

  @ParameterizedTest
  @ValueSource(strings = {
    "valid.xml"
    //todo: add more test cases
  })
  void validate_validInput_shouldNotThrowException(String fileName) throws IOException, SAXException {
    when(messageConsumerProperties.getNoteXsdPath()).thenReturn("note-format.xsd");
    var inputAsString = Resources.getResourceAsString(fileName);
    try (var source = new ByteArrayInputStream(inputAsString.getBytes())) {
      var validator = messageConsumerConfiguration.initXsdValidator();
      assertThatNoException().isThrownBy(() -> validator.validate(new StreamSource(source)));
    }
  }

  @ParameterizedTest
  @ValueSource(strings = {
    "invalid.xml"
    //todo: add more test cases
  })
  void validate_invalidInput_shouldThrowException(String fileName) throws IOException, SAXException {
    when(messageConsumerProperties.getNoteXsdPath()).thenReturn("note-format.xsd");
    var inputAsString = Resources.getResourceAsString(fileName);
    try (var source = new ByteArrayInputStream(inputAsString.getBytes())) {
      var validator = messageConsumerConfiguration.initXsdValidator();
      assertThatThrownBy(() -> validator.validate(new StreamSource(source)))
        .isInstanceOf(SAXParseException.class);
    }
  }
}
