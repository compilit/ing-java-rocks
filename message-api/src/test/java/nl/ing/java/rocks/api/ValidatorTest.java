package nl.ing.java.rocks.api;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.io.IOException;
import javax.xml.transform.stream.StreamSource;
import nl.ing.java.rocks.api.MessageConsumerConfiguration;
import nl.ing.java.rocks.api.MessageConsumerProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

  @Test
  void validate_validInput_shouldNotThrowException() throws IOException, SAXException {
    when(messageConsumerProperties.getNoteXsdPath()).thenReturn("note-format.xsd");
    var inputStream = getClass().getClassLoader().getResourceAsStream("valid.xml");
    var validator = messageConsumerConfiguration.initXsdValidator();
    assertThatNoException().isThrownBy(() -> validator.validate(new StreamSource(inputStream)));
  }

  @Test
  void validate_invalidInput_shouldThrowException() throws IOException, SAXException {
    when(messageConsumerProperties.getNoteXsdPath()).thenReturn("note-format.xsd");
    var inputStream = getClass().getClassLoader().getResourceAsStream("invalid.xml");
    var validator = messageConsumerConfiguration.initXsdValidator();
    assertThatThrownBy(() -> validator.validate(new StreamSource(inputStream)))
      .isInstanceOf(SAXParseException.class);
  }
}
