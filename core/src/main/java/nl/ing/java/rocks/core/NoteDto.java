package nl.ing.java.rocks.core;
public class NoteDto {

  private String from;
  private String to;
  private String heading;
  private String body;

  public void setFrom(String from) {
    this.from = from;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public void setHeading(String heading) {
    this.heading = heading;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getFrom() {
    return from;
  }

  public String getTo() {
    return to;
  }

  public String getHeading() {
    return heading;
  }

  public String getBody() {
    return body;
  }
}
