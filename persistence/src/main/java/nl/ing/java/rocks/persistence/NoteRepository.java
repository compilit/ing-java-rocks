package nl.ing.java.rocks.persistence;

import java.util.List;
import nl.ing.java.rocks.entities.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface NoteRepository extends CrudRepository<Note, Long> {

  List<Note> findAllByHeading(String heading);
}
