package nl.ing.java.rocks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
public class MessageConsumer {

  private final Handler<Note> noteHandler;
  private final Validator<String> noteValidator;
  private final ObjectMapper objectMapper;

  public MessageConsumer(Handler<Note> noteHandler,
                         Validator<String> noteValidator,
                         ObjectMapper objectMapper) {
    this.noteHandler = noteHandler;
    this.noteValidator = noteValidator;
    this.objectMapper = objectMapper;
  }

  @KafkaListener(topics = {"Reminder"})
  public void consume(String noteInput) {
    noteValidator.validate(noteInput);
    var note = objectMapper.convertValue(noteInput, Note.class);
    noteHandler.handle(note);
  }

}
