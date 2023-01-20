package nl.ing.java.rocks;

import static org.springframework.util.ResourceUtils.getFile;

import java.io.FileNotFoundException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xml.sax.SAXException;

@Configuration
public class CoreConfiguration {

  private final CoreProperties coreProperties;

  public CoreConfiguration(CoreProperties coreProperties) {this.coreProperties = coreProperties;}

  @Bean
  Validator initXsdValidator() throws SAXException, FileNotFoundException {
    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    Source schemaFile = new StreamSource(getFile(coreProperties.getNoteXsdPath()));
    Schema schema = factory.newSchema(schemaFile);
    return schema.newValidator();
  }
}
