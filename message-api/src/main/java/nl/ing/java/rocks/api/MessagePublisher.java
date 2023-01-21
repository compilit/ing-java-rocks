package nl.ing.java.rocks.api;

import nl.ing.java.rocks.core.NoteDto;
import nl.ing.java.rocks.core.api.JmsPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
class MessagePublisher implements JmsPublisher<NoteDto> {

  private final RabbitTemplate rabbitTemplate;
  private final MessageConsumerProperties messageConsumerProperties;

  MessagePublisher(RabbitTemplate rabbitTemplate, MessageConsumerProperties messageConsumerProperties) {
    this.rabbitTemplate = rabbitTemplate;
    this.messageConsumerProperties = messageConsumerProperties;
  }

  public void publish(NoteDto note) {
    rabbitTemplate.convertAndSend(messageConsumerProperties.getExchangeName(), messageConsumerProperties.getName(), note);
  }
}
