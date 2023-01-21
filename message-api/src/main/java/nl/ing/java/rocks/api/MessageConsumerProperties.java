package nl.ing.java.rocks.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("mq")
public class MessageConsumerProperties {

  private String noteXsdPath;
  private String name;
  private String exchangeName;

  public String getNoteXsdPath() {
    return noteXsdPath;
  }

  public void setNoteXsdPath(String noteXsdPath) {
    this.noteXsdPath = noteXsdPath;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getExchangeName() {
    return exchangeName;
  }

  public void setExchangeName(String exchangeName) {
    this.exchangeName = exchangeName;
  }
}
