package nl.ing.java.rocks.api;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import nl.ing.java.rocks.core.api.Handler;
import nl.ing.java.rocks.core.api.JmsConsumer;
import nl.ing.java.rocks.core.NoteDto;
import nl.ing.java.rocks.core.api.Validator;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
class MessageConsumer implements JmsConsumer<GenericMessage<NoteDto>> {

  private final Handler<NoteDto> noteHandler;
  private final Validator<NoteDto> noteValidator;
  private final XmlMapper objectMapper;

  public MessageConsumer(Handler<NoteDto> noteHandler,
                         Validator<NoteDto> noteValidator,
                         XmlMapper objectMapper) {
    this.noteHandler = noteHandler;
    this.noteValidator = noteValidator;
    this.objectMapper = objectMapper;
  }

  @RabbitListener(queues = {"${mq.name}"})
  public void consume(GenericMessage<NoteDto> message) {
    var noteDto = message.getPayload();
    noteValidator.validate(noteDto);
    noteHandler.handle(noteDto);
  }

}
