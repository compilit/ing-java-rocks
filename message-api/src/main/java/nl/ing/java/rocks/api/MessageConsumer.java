package nl.ing.java.rocks.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import nl.ing.java.rocks.core.JavaRocksException;
import nl.ing.java.rocks.core.NoteDto;
import nl.ing.java.rocks.core.api.Handler;
import nl.ing.java.rocks.core.api.JmsConsumer;
import nl.ing.java.rocks.core.api.Validator;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
class MessageConsumer implements JmsConsumer<GenericMessage<String>> {

  private final Handler<NoteDto> noteHandler;
  private final Validator<NoteDto> noteValidator;
  private final XmlMapper xmlMapper;

  public MessageConsumer(Handler<NoteDto> noteHandler,
                         Validator<NoteDto> noteValidator,
                         XmlMapper xmlMapper) {
    this.noteHandler = noteHandler;
    this.noteValidator = noteValidator;
    this.xmlMapper = xmlMapper;
  }

  @RabbitListener(queues = {"${mq.name}"})
  public void consume(GenericMessage<String> message) {
    try {
      var noteDto = xmlMapper.readValue(message.getPayload(), NoteDto.class);
      noteValidator.validate(noteDto);
      noteHandler.handle(noteDto);
    } catch (JsonProcessingException e) {
      throw new JavaRocksException(e);
    }
  }

}
