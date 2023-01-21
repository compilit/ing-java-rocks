package nl.ing.java.rocks.persistence;

import nl.ing.java.rocks.core.api.PersistencePort;
import nl.ing.java.rocks.entities.Note;
import org.springframework.stereotype.Component;

@Component
class NotePostgreSQLAdapter implements PersistencePort<Note> {

  private final NoteRepository noteRepository;

  NotePostgreSQLAdapter(NoteRepository noteRepository) {this.noteRepository = noteRepository;}

  @Override
  public Note persist(Note input) {
    return noteRepository.save(input);
  }

}
