package nl.ing.java.rocks.persistence;

import java.util.List;
import nl.ing.java.rocks.core.api.PersistencePort;
import nl.ing.java.rocks.core.api.RetrievalPort;
import nl.ing.java.rocks.entities.Note;
import org.springframework.stereotype.Component;

@Component
class NoteDatabaseAdapter implements PersistencePort<Note>, RetrievalPort<List<Note>, String> {

  private final NoteRepository noteRepository;

  NoteDatabaseAdapter(NoteRepository noteRepository) {this.noteRepository = noteRepository;}

  @Override
  public Note persist(Note input) {
    return noteRepository.save(input);
  }

  @Override
  public List<Note> retrieveBy(String identifyingProperty) {
    return noteRepository.findAllByHeading(identifyingProperty);
  }
}
