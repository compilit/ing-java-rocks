package nl.ing.java.rocks.api;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.xml.transform.stream.StreamSource;
import nl.ing.java.rocks.core.JavaRocksException;
import nl.ing.java.rocks.core.NoteDto;
import nl.ing.java.rocks.core.api.Validator;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

@Component
class XsdValidator implements Validator<NoteDto> {

  private final javax.xml.validation.Validator validator;
  private final XmlMapper xmlMapper;

  XsdValidator(javax.xml.validation.Validator validator, XmlMapper xmlMapper) {
    this.validator = validator;
    this.xmlMapper = xmlMapper;
  }

  @Override
  public void validate(NoteDto input) {
    try {
      var inputAsString = xmlMapper.writeValueAsString(input);
      validator.validate(new StreamSource(new ByteArrayInputStream(inputAsString.getBytes())));
    } catch (SAXException e) {
      throw new InvalidNoteFormatException("The presented message did not have the right format");
    } catch (IOException e) {
      throw new JavaRocksException(e);
    }
  }
}
