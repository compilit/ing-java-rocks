package nl.ing.java.rocks;

public class Note {

  private final String from;
  private final String to;
  private final String heading;
  private String body;

  public Note(String from, String to, String heading, String body) {
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

  public void updateBody(String body) {
    this.body = body;
  }

  static class Builder {

    private String from;
    private String to;
    private String heading;
    private String body;
    public static Builder builder() {
      return new Builder();
    }

    public Builder from(String from) {
      this.from = from;
      return this;
    }

    public Builder to(String to) {
      this.to = to;
      return this;
    }

    public Builder heading(String heading) {
      this.heading = heading;
      return this;
    }

    public Builder body(String body) {
      this.body = body;
      return this;
    }

    public Note build() {
      return new Note(from, to, heading, body);
    }
  }
}
