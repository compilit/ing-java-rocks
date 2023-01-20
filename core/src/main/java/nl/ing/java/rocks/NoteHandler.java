package nl.ing.java.rocks;

class NoteHandler implements Handler<Note> {

  private final PersistencePort<Note> persistencePort;

  NoteHandler(PersistencePort<Note> persistencePort) {this.persistencePort = persistencePort;}

  @Override
  public void handle(Note input) {
    //todo: mutate body
    persistencePort.persist(input);
  }
}
