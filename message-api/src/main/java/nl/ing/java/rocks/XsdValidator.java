package nl.ing.java.rocks;


import java.io.IOException;
import javax.xml.transform.stream.StreamSource;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

@Component
class XsdValidator implements Validator<String> {

  private final javax.xml.validation.Validator validator;

  XsdValidator(javax.xml.validation.Validator validator) {this.validator = validator;}

  @Override
  public void validate(String input) {
    try {
      validator.validate(new StreamSource(input));
    } catch (IOException e) {
      throw new JavaRocksException(e);
    } catch (SAXException e) {
      throw new InvalidNoteFormatException("The presented message did not have the right format");
    }
  }
}
