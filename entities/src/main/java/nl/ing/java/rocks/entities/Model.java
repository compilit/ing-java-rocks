package nl.ing.java.rocks.entities;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

@MappedSuperclass
public class Model {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "date_created")
  private Instant dateCreated = Instant.now();

  @Column(name = "date_modified")
  private Instant dateModified = Instant.now();

  @PrePersist
  public void updateDateModified() {
    dateModified = Instant.now();
  }
}
