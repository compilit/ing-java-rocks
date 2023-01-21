package nl.ing.java.rocks.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import nl.ing.java.rocks.core.NoteDto;
import nl.ing.java.rocks.core.api.JmsPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
class MessagePublisher implements JmsPublisher<NoteDto> {

  private final RabbitTemplate rabbitTemplate;
  private final MessageConsumerProperties messageConsumerProperties;
  private final XmlMapper xmlMapper;
  private final Logger logger = LoggerFactory.getLogger(getClass());

  MessagePublisher(RabbitTemplate rabbitTemplate, MessageConsumerProperties messageConsumerProperties,
                   XmlMapper xmlMapper) {
    this.rabbitTemplate = rabbitTemplate;
    this.messageConsumerProperties = messageConsumerProperties;
    this.xmlMapper = xmlMapper;
  }

  public void publish(NoteDto note) {
    try {
      var noteAsString = xmlMapper.writeValueAsString(note);
      rabbitTemplate.convertAndSend(
        messageConsumerProperties.getExchangeName(),
        messageConsumerProperties.getName(),
        noteAsString
      );
    } catch (JsonProcessingException e) {
      logger.error("Unable to create string message from note dto {}", note);
    }
  }
}
