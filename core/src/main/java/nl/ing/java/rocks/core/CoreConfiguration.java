package nl.ing.java.rocks.core;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CoreConfiguration {

  @Bean
  XmlMapper objectMapper() {
    return new XmlMapper();
  }
}
