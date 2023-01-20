package nl.ing.java.rocks;

public class NoteDto {
  private final String from;
  private final String to;
  private final String heading;
  private final String body;

  public NoteDto(String from, String to, String heading, String body) {
    this.from = from;
    this.to = to;
    this.heading = heading;
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
