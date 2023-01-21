package nl.ing.java.rocks.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "notes")
public class Note extends Model {

  @Column(name = "from_sender")
  private String from;
  @Column(name = "to_recipient")
  private String to;
  @Column(name = "heading_text")
  private String heading;
  @Column(name = "body_text")
  private String body;

  public Note() {
  }

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

}
