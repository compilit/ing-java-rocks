package nl.ing.java.rocks.core;

import nl.ing.java.rocks.core.api.Handler;
import nl.ing.java.rocks.core.api.PersistencePort;
import nl.ing.java.rocks.entities.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class NoteBodyUpdateHandler implements Handler<NoteDto> {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final PersistencePort<Note> persistencePort;

  NoteBodyUpdateHandler(PersistencePort<Note> persistencePort) {
    this.persistencePort = persistencePort;
  }

  @Override
  public void handle(NoteDto input) {
    logger.info("Handling update for {}", input);
    Note note = createNote(input);
    String upperCasedBody = mutateBody(note);
    note.updateBody(upperCasedBody);
    persistencePort.persist(note);
    logger.info("Handling update for {} finished", input);
  }

  private static Note createNote(NoteDto input) {
    return new Note(input.getFrom(), input.getTo(), input.getHeading(), input.getBody());
  }

  private static String mutateBody(Note note) {
    var outdatedBody = note.getBody();
    var bodyParts = outdatedBody.split(" ");
    var javaPart = bodyParts[0];
    var opinionPart = bodyParts[1];
    var updatedBody = javaPart + " 11 " + opinionPart;
    return updatedBody.toUpperCase();
  }
}
