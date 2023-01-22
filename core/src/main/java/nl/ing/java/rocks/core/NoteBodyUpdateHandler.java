package nl.ing.java.rocks.core;

import nl.ing.java.rocks.core.api.Handler;
import nl.ing.java.rocks.core.api.PersistencePort;
import nl.ing.java.rocks.entities.Note;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
class NoteBodyUpdateHandler implements Handler<NoteDto>, MessageHandler {

  private final PersistencePort<Note> persistencePort;

  NoteBodyUpdateHandler(PersistencePort<Note> persistencePort) {
    this.persistencePort = persistencePort;
  }

  @Override
  public void handle(NoteDto input) {
    var note = createNote(input);
    var outdatedBody = note.getBody();
    var bodyParts = outdatedBody.split(" ");
    var javaPart = bodyParts[0];
    var opinionPart = bodyParts[1];
    var updatedBody = javaPart + " 11 " + opinionPart;
    var upperCasedBody = updatedBody.toUpperCase();
    note.updateBody(upperCasedBody);
    persistencePort.persist(note);
  }

  @Override
  public void handleMessage(Message<?> message) throws MessagingException {

  }

  private static Note createNote(NoteDto input) {
    return new Note(input.getFrom(), input.getTo(), input.getHeading(), input.getBody());
  }
}
