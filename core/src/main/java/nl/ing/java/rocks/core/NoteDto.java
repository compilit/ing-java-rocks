package nl.ing.java.rocks.core;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("note")
public class NoteDto {

  private String from;
  private String to;
  private String heading;
  private String body;

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getHeading() {
    return heading;
  }

  public void setHeading(String heading) {
    this.heading = heading;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
