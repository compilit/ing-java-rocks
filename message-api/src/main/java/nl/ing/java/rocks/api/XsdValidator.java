package nl.ing.java.rocks.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import javax.xml.transform.stream.StreamSource;
import nl.ing.java.rocks.core.JavaRocksException;
import nl.ing.java.rocks.core.NoteDto;
import nl.ing.java.rocks.core.api.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

@Component
class XsdValidator implements Validator<NoteDto> {

  private final javax.xml.validation.Validator validator;
  private final ObjectMapper objectMapper;
  private final Logger logger = LoggerFactory.getLogger(getClass());

  XsdValidator(javax.xml.validation.Validator validator, ObjectMapper objectMapper) {this.validator = validator;
    this.objectMapper = objectMapper;
  }

  @Override
  public void validate(NoteDto input) {
    try {
    var inputAsString = objectMapper.writeValueAsString(input);
      validator.validate(new StreamSource(new ByteArrayInputStream(inputAsString.getBytes())));
    } catch (SAXException e) {
      var message = "The presented message did not have the right format";
      logger.error(message);
      throw new InvalidNoteFormatException("The presented message did not have the right format");
    }catch (Exception e) {
      logger.error("Validation did not go as expected");
      throw new JavaRocksException(e);
    }
  }
}
