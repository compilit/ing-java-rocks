package nl.ing.java.rocks;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("core")
public class CoreProperties {
  private String noteXsdPath;

  public String getNoteXsdPath() {
    return noteXsdPath;
  }
}
